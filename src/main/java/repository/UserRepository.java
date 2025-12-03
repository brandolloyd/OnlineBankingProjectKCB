package repository;

import org.springframework.data.repository.CrudRepository;
// CRUD (Create,Read,Update,Delete) operations in java, provides
// all the tools needed for database interaction
import entity.User;
import java.util.Optional;
// Error handling utility for results

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
