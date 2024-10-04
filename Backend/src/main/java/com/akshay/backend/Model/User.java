package com.akshay.backend.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")  // Changed to plural for convention
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Changed to IDENTITY for better performance
    private Integer id;

    @Column(nullable = false, unique = true)
    private String userName;

    private String name;

    @ElementCollection
    @CollectionTable(name = "user_followers", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "follower_id")
    private List<Integer> followers = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "user_followings", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "following_id")
    private List<Integer> followings = new ArrayList<>();

    @OneToMany
    @JoinTable(
            name = "user_saved_posts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> savedPost = new ArrayList<>();


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    private String gender;

    @Enumerated(EnumType.STRING)
    private Role role;  // Changed to singular, assuming a user has one role


}