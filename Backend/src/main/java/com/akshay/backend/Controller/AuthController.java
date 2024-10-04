package com.akshay.backend.Controller;

import com.akshay.backend.Model.User;
import com.akshay.backend.Repository.UserRepository;
import com.akshay.backend.Request.LoginRequest;
import com.akshay.backend.Response.AuthResponse;
import com.akshay.backend.Security.JwtProvider;
import com.akshay.backend.Security.Service.CustomUserDetailsService;
import com.akshay.backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public AuthResponse createUser(@RequestBody User user) throws Exception {


        if (userRepository.existsByUserName(user.getUserName())) {
            throw new Exception("Username is not available");
        }
        if(userRepository.findByEmail(user.getEmail()).isPresent())
        {
            throw new Exception("This email is already linked with another account");
        }


        List<GrantedAuthority> authority= List.of(new SimpleGrantedAuthority(String.valueOf(user.getRole())));
        Authentication authentication =new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword(),authority);

        String token= JwtProvider.generateToken(authentication);
        if(!token.isEmpty())
        {
            userService.registerUser(user);
        }
        return new AuthResponse(token,"User registered Successfully");
    }

    private Authentication authenticate(String email, String password)
    {
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(email);
        if (userDetails == null)
        {
            throw new BadCredentialsException("Invalid Email id");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword()))
        {
            throw new BadCredentialsException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody LoginRequest loginRequest)
    {
        Authentication authentication =authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token= JwtProvider.generateToken(authentication);
        return new AuthResponse(token,"User logged-in Successfully");
    }

    @PostMapping("/saveAllUsers")
    public List<User> saveAll(@RequestBody List<User> users)
    {
        return userRepository.saveAll(users);
    }

}
