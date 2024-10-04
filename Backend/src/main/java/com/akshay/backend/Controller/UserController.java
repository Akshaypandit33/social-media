package com.akshay.backend.Controller;

import com.akshay.backend.Exceptions.UserException;
import com.akshay.backend.Model.User;
import com.akshay.backend.Repository.UserRepository;
import com.akshay.backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users") // Added base path for consistency
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;




    @GetMapping("/get")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userid) {
        try {
            User user = userService.findUserById(userid);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/e/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        try {
            User user = userService.findUserByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/follow/{userid2}")
    public ResponseEntity<User> followUserHandler(@RequestHeader("Authorization") String token, @PathVariable Integer userid2) {
        try {
            Integer userid1= userService.getUserFromToken(token).getId();
            User user = userService.followUser(userid1, userid2);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public User updateUser(@RequestHeader("Authorization") String token,@RequestBody User user, Integer userid) throws UserException {
        User user1  = userService.getUserFromToken(token);
        if (user1 == null){
            throw new UserException("can not update");
        }

        return userService.updateUser(user,user1.getId());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            String result = userService.deleteUser(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<User>> searchUser(@PathVariable("query") String query) {
        List<User> users = userService.searchUser(query);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public User getUserFromToken(@RequestHeader ("Authorization") String token) throws UserException {
        System.out.println("jwt token--------------------"+token);

        User user=userService.getUserFromToken(token);
        if (user !=null)
        {
            user.setPassword(null);
            return user;
        }
        return null;
    }
}
