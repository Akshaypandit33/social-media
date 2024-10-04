package com.akshay.backend.ServiceImplementation;

import com.akshay.backend.Exceptions.UserException;
import com.akshay.backend.Model.Role;
import com.akshay.backend.Model.User;
import com.akshay.backend.Repository.UserRepository;
import com.akshay.backend.Security.JwtProvider;
import com.akshay.backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User registerUser(User user) {
        User newUser= new User();
        newUser.setUserName(user.getUserName());
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(Role.ROLE_USER);
        newUser.setGender(user.getGender());
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public User findUserById(Integer userId) throws UserException {
        // first we will check whether the user exists or not for which we will be using  optional
        Optional<User> user=userRepository.findById(userId);

        if(user.isEmpty())
        {
            throw new UserException("User not found");
        }

        return user.get();
    }

    @Override
    public User findUserByEmail(String email) throws UserException {

        Optional<User> user =userRepository.findByEmail(email);
        if (user.isEmpty())
        {
            throw new UserException("User not found");
        }

        return user.get();
    }

    @Override
    public User findUserByUser_name(String username) throws UserException {
        Optional<User> user=userRepository.findByUserName(username);
        if(user.isEmpty())
        {
            throw new UserException("User not found");
        }

        return user.get();
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws UserException {
        User user1= findUserById(userId1);
        User user2=findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }

    @Override
    public User updateUser(User user,Integer userId) throws UserException {
        Optional<User> user1= userRepository.findById(userId);
        if (user1.isEmpty())
        {
            throw new UserException("User not Found");
        }
        User CurrUser= user1.get();

        if (user.getUserName() !=null)
        {
            CurrUser.setUserName(user.getUserName());
        }
        if (user.getName() !=null)
        {
            CurrUser.setName(user.getName());
        }
        if (user.getEmail() != null)
        {
            CurrUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null)
        {
            CurrUser.setPassword(user.getPassword());
        }
        if(user.getGender() !=null)
        {
            CurrUser.setGender(user.getGender());
        }

        return userRepository.save(CurrUser);
    }

    @Override
    public String deleteUser(Integer id) throws UserException {
        Optional<User> user= userRepository.findById(id);

        if  (user.isEmpty())
        {
            throw new UserException("User not found");
        }
        userRepository.delete(user.get());
        return "User Deleted Successfully";
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public boolean existByUserName(String name) {
        return userRepository.existsByUserName(name);
    }

    @Override
    public User getUserFromToken(String token) {
        String email=JwtProvider.extractEmail(token);
        if (!email.isEmpty())
        {
            return userRepository.findByEmail(email).get();
        }

        return null;
    }

}
