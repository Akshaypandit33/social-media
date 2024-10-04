package com.akshay.backend.Service;

import com.akshay.backend.Exceptions.UserException;
import com.akshay.backend.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
     User registerUser(User user);

     User findUserById(Integer userId) throws UserException;
     User findUserByEmail(String email) throws UserException;
    User findUserByUser_name(String username) throws UserException;
    User followUser(Integer user1, Integer user2) throws UserException;

     User updateUser(User user, Integer userId) throws UserException;
    String deleteUser(Integer userId) throws UserException;
    List<User> searchUser(String query);
    boolean existByUserName(String name);
    User getUserFromToken(String token);
}
