package laura.example.springboot.controller;

import laura.example.springboot.model.User;
import laura.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for accessing service methods.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Calls the method of the service to be able to register a user in BDD
     * @param user : user to register in BDD
     * @return The user who has been registered
     */
    @PostMapping("/saveuser")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    /**
     * Calls the method of the service to be able to retrieve all registered users in DB
     * @return An iterable of users
     */
    @GetMapping("/getusers")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Calls the method of the service to be able to retrieve the user by his identifier in DB
     * @param id : found user id
     * @return User found
     */
    @GetMapping("/getuserbyid/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.orElse(null);
    }

    /**
     * Calls the method of the service to be able to retrieve the user by his name in DB
     * @param name : found user name
     * @return User found
     */
    @GetMapping("/getuserbyname/{name}")
    public User getUserByName(@PathVariable("name") String name) {
        Optional<User> user = userService.getUserByName(name);
        return user.orElse(null);
    }
}
