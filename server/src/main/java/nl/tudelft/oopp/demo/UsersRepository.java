package nl.tudelft.oopp.demo;


import org.springframework.data.repository.CrudRepository;
import nl.tudelft.oopp.demo.Users;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UsersRepository extends CrudRepository<Users, Integer> {

}
