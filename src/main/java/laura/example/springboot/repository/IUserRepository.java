package laura.example.springboot.repository;

import laura.example.springboot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<User,Long> {

    Optional<User> findUserByName(String name);

}
