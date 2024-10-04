package com.akshay.backend.Repository;

import com.akshay.backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String username);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
    @Query("select u from User u where u.name LIKE %:query% OR u.userName LIKE %:query% OR u.email LIKE %:query%")
    List<User> searchUser(@Param("query") String query);
}
