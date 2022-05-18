package laura.example.springboot.service;

import laura.example.springboot.exception.AgeException;
import laura.example.springboot.exception.CountryException;
import laura.example.springboot.exception.GenderException;
import laura.example.springboot.model.EGender;
import laura.example.springboot.model.User;
import laura.example.springboot.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
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
        user.setAge(calculateAge(user.getBirthdate()));
        if(user.getAge()<18){
            throw new AgeException(String.format("%s isn't major",user.getName()));
        }else if(!user.getCountry().equalsIgnoreCase("france")){
            throw new CountryException(String.format("%s don't live in France",user.getName()));
        }else if(!(user.getGender().equalsIgnoreCase(EGender.FEMALE.name()) || user.getGender().equalsIgnoreCase(EGender.MALE.name()))){
            throw new GenderException("Gender must be written as 'FEMALE' or 'MALE'");
        }else{
            return userRepository.save(user);
        }
    }

    private int calculateAge(Date birthDate){
        LocalDate birthdateLocalDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birthdateLocalDate,LocalDate.now(ZoneId.systemDefault())).getYears();

    }

}
