package com.akshay.backend.Controller;

import com.akshay.backend.Model.Reels;
import com.akshay.backend.Model.User;
import com.akshay.backend.Service.ReelsService;
import com.akshay.backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reels")
public class ReelsController {

    private final ReelsService reelsService;
    private final UserService userService;

    @PostMapping("/create")
    public Reels createReels(@RequestBody Reels reels, @RequestHeader("Authorization") String token)
    {
        User user= userService.getUserFromToken(token);
        return reelsService.createReels(reels,user);
    }

    @GetMapping
    public List<Reels> findAllReels()
    {
        return reelsService.findAllReels();
    }

    @GetMapping("/users/{userId}")
    public List<Reels> findReelsByUserId(@PathVariable Integer userId) throws Exception {
        return reelsService.findUserReels(userId);
    }

    @DeleteMapping("/delete/{reelsId}")
    public String deleteReels(@PathVariable Integer reelsId, @RequestHeader("Authorization") String token) throws Exception {
        Integer userId=userService.getUserFromToken(token).getId();
        return reelsService.deleteReels(userId,reelsId);
    }
}
