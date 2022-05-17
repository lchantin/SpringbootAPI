package laura.example.springboot.repository;

import laura.example.springboot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This class has the role of communicating with the BDD to manage the CRUD and the different methods allowing to manipulate a User.
 */
@Repository
public interface IUserRepository extends CrudRepository<User,Long> {

    /**
     * Allows you to search for a user in the database according to his name
     * @param name : name to search
     * @return an optional for the user found
     */
    Optional<User> findUserByName(String name);

}
