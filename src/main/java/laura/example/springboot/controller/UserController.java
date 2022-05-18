package laura.example.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import laura.example.springboot.exception.AgeException;
import laura.example.springboot.exception.CountryException;
import laura.example.springboot.exception.ErrorMessage;
import laura.example.springboot.exception.GenderException;
import laura.example.springboot.model.User;
import laura.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for accessing service methods.
 */
@RestController
@RequestMapping("/users")
@Api("Rest Controller for user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Calls the method of the service to be able to register user in BDD
     * @param user : user to register in BDD
     * @return The user who has been registered
     */
    @PostMapping("/saveuser")
    @ApiOperation(value = "Save user into BDD")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "User created successfully"), @ApiResponse(code = 404, message = "Bad parameters"),
            @ApiResponse(code = 500, message = "Internal server error") })
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
      }

    /**
     * Calls the method of the service to be able to retrieve all registered users in DB
     * @return An iterable of users
     */
    @GetMapping("/getusers")
    @ApiOperation(value = "Search and show users register into BDD")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "List of users successfully found"), @ApiResponse(code = 404, message = "Bad parameters"),
            @ApiResponse(code = 500, message = "Internal server error") })
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Calls the method of the service to be able to retrieve the user by his identifier in DB
     * @param id : found user id
     * @return User found
     */
    @GetMapping("/getuserbyid/{id}")
    @ApiOperation(value = "Search and show user register into BDD by id ")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "user successfully found"), @ApiResponse(code = 404, message = "Bad parameters"),
            @ApiResponse(code = 500, message = "Internal server error") })
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
    @ApiOperation(value = "Search and show user register into BDD by name ")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "user successfully found"), @ApiResponse(code = 404, message = "Bad parameters"),
            @ApiResponse(code = 500, message = "Internal server error") })
    public User getUserByName(@PathVariable("name") String name) {
        Optional<User> user = userService.getUserByName(name);
        return user.orElse(null);
    }


    /**
     * Method called during a AgeException allowing to return the custom error message
     * @param ex : exception rise
     * @return an ErrorMessage with origin
     */
    @ExceptionHandler(value = AgeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleAgeException(AgeException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * Method called during a CountryException allowing to return the custom error message
     * @param ex : exception rise
     * @return an ErrorMessage with origin
     */
    @ExceptionHandler(value = CountryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleCountryException(CountryException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * Method called during a GenderException allowing to return the custom error message
     * @param ex : exception rise
     * @return an ErrorMessage with origin
     */
    @ExceptionHandler(value = GenderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleGenderException(GenderException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
