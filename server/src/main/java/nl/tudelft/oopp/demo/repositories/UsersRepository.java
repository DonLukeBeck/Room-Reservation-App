package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UsersRepository extends JpaRepository<Users, Long> {
   @Query(value = "SELECT * FROM Users WHERE netid = ?1 AND password = ?2 LIMIT 1", nativeQuery = true)
   Users findUserByNetidAndPass(String netid, String password);

   @Query(value = "SELECT * FROM Users WHERE netid = ?1 LIMIT 1", nativeQuery = true)
   Users findUserByNetid(String netid);
}
