package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Services.IUserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;
    @PostMapping("/add")
    User addUser(@RequestBody User user)
    {
        return userService.addOrUpdateUser(user);
    }
    @PutMapping("/update")
    User updateUser(@RequestBody User user){
        return userService.addOrUpdateUser(user);
    }
    @GetMapping("/get/{id}")
    User getUser(@PathVariable("id") Integer id){
        return userService.retrieveUser(id);
    }
    @GetMapping("/all")
    List<User> getAllUsers(){return userService.retrieveAllUsers();}
    @DeleteMapping("/delete/{id}")
    void deleteUser(@PathVariable("id") Integer id){
        userService.removeUser(id);
    }

}
