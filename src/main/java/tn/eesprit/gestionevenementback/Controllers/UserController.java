package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.ERole;
import tn.eesprit.gestionevenementback.Entities.Event;
import tn.eesprit.gestionevenementback.Entities.Role;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Exception.ResourceNotFoundException;
import tn.eesprit.gestionevenementback.Payload.request.SignupRequest;
import tn.eesprit.gestionevenementback.Payload.request.UserRequest;
import tn.eesprit.gestionevenementback.Payload.response.MessageResponse;
import tn.eesprit.gestionevenementback.Repository.RoleRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;
import tn.eesprit.gestionevenementback.Services.EmailService;
import tn.eesprit.gestionevenementback.Services.IUserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    private final IUserService userService;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final RoleRepository roleRepository;
    @GetMapping("/get-users")
    public ResponseEntity<List<User>> getUsers( )  {
        List<User> users = userRepository.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);

    }



    @GetMapping("/get-user/{id}")
    public ResponseEntity<User> getEvent(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
    @PutMapping("/active-user/{id}")
    public ResponseEntity<User> activeAccount(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));
        user.setActive(true);
        userRepository.save(user);
      String status=  this.emailService.sendSimpleMail(user.getEmail());
      System.out.println(status);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
    @PutMapping("/desactive-user/{id}")
    public ResponseEntity<User> desactiveAccount(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));
        user.setActive(false);
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> getEvent(@PathVariable(value = "id") Long id,@RequestBody User user) throws ResourceNotFoundException {
        User _user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));
        if(user.getPassword()!=""){
            _user.setPassword(encoder.encode(user.getPassword()));
        }
        _user.setFirstName(user.getFirstName());
        _user.setLastName(user.getLastName());
        _user.setEmail(user.getEmail());
        _user.setPhone(user.getPhone());
        _user.setUsername(user.getUsername());
        userRepository.save(_user);

        return new ResponseEntity<>(_user, HttpStatus.OK);

    }
    @GetMapping("/all")
    List<User> getAllUsers(){return userService.retrieveAllUsers();}
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/new-user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account

        User user = new User(userRequest.getUsername(),
                userRequest.getEmail(),userRequest.getFirstName(),userRequest.getLastName(),
                userRequest.getPhone(),encoder.encode(userRequest.getPassword())

                );

        Set<String> strRoles = userRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.membre)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.admin)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "membre":
                        Role membreRole = roleRepository.findByName(ERole.membre)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(membreRole);

                        break;


                }

            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

