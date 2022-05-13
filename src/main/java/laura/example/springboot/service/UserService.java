package laura.example.springboot.service;

import laura.example.springboot.model.User;
import laura.example.springboot.repository.IUserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public Optional<User> getUserById(final Long id){
        return userRepository.findById(id);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByName(String name) {
        return userRepository.findUserByName(name);
    }


    public User saveUser(User user) {

        //Vérifier s'il est majeur et qu'il vit en france
        return userRepository.save(user);
    }

}
