package laura.example.springboot.service;

import laura.example.springboot.model.User;
import laura.example.springboot.repository.IUserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service allowing to interact with the repository to manage the interactions of a user.
 */
@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    /**
     * Calls the method of the repository to be able to retrieve the user by his identifier in DB
     * @param id : found user id
     * @return User found
     */
    public Optional<User> getUserById(final Long id){
        return userRepository.findById(id);
    }

    /**
     * Calls the method of the repository to be able to retrieve all registered users in DB
     * @return An iterable of users
     */
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Calls the method of the repository to be able to retrieve the user by his name in DB
     * @param name : found user name
     * @return User found
     */
    public Optional<User> getUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    /**
     * Calls the method of the repository to be able to register a user in BDD
     * @param user : user to register in BDD
     * @return The user who has been registered
     */
    public User saveUser(User user) {

        //Vérifier s'il est majeur et qu'il vit en france
        return userRepository.save(user);
    }

}
