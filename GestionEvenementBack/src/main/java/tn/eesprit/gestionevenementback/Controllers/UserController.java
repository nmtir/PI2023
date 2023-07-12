package tn.eesprit.gestionevenementback.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)

@Tag(name = "User", description = "User management APIs")
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

    @PostMapping("/add")
    User addUser(@RequestBody User user)
    {
        return userService.addOrUpdateUser(user);
    }
    @PutMapping("/update")
    User updateUser(@RequestBody User user){
        return userService.addOrUpdateUser(user);
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
      String status=  emailService.sendSimpleMail(user.getEmail());
      System.out.println(status);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
    @PutMapping("/desactive-user/{id}")
    public ResponseEntity<?> desactiveAccount(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
       Optional< User> user = userRepository.findById(id);
       if(user.isPresent()) {

           user.get().setActive(false);
           userRepository.save(user.get());
           return new ResponseEntity<>(user, HttpStatus.OK);
       }else{
           return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
       }
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

    @GetMapping("/search/{value}")
    public  ResponseEntity<List<User>> serach(@PathVariable String value){
        return new ResponseEntity<>(userService.sarch(value), HttpStatus.OK);
    }

    @PutMapping("/updateBlocked/{username}")
    public void updateBlocked(@PathVariable String username){

        Optional<User> user=userRepository.findByUsername(username);
        if(user.isPresent()){
            User _user=user.get();

            _user.setNbIteration(_user.getNbIteration()+1);
            if(_user.getNbIteration()>=3){
                _user.setBlocked(true);
            }

            System.out.println("xxxxxxxxxx + " + _user.getNbIteration());

            userRepository.save(_user);
        }
    }
    @PutMapping("/nbIteration/{id}")
    public void nbIteration(@PathVariable Long id){
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent()){
            User _user=user.get();
            _user.setNbConnexion(_user.getNbConnexion()+1);
            userRepository.save(_user);
        }
    }
    @PutMapping("/forgetPassword/{email}")
    public ResponseEntity<?> forgetPassword(@PathVariable("email") String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User not foudn !!"));
        }else {
        String token = UUID.randomUUID().toString();
        System.out.println(token);
        emailService.sendMailReset(user.get().getEmail(),token);
        User _user=user.get();
          _user.setToken(token);
         userRepository.save(_user);
        return ResponseEntity
                .ok()
                .body(_user);


    }
    }
    @PutMapping("/resetPassword/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable("token") String token,@RequestBody String newPassword) {
       System.out.println(token);
        System.out.println(newPassword);
        Optional<User> user = userRepository.findByToken(token);
        if (!user.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User not foudn !!"));
        }else {

           // emailService.sendMailReset(user.get().getEmail(),token);
            User _user=user.get();
            System.out.println(_user.toString());
            _user.setToken("");
            System.out.println(newPassword);
            _user.setPassword(encoder.encode(newPassword));
            System.out.println(_user.toString());
            userRepository.save(_user);
            return ResponseEntity
                    .ok()
                    .body(_user);


        }
    }
}

