package com.akshay.backend.Controller;

import com.akshay.backend.Model.Story;
import com.akshay.backend.Model.User;
import com.akshay.backend.Service.StoryService;
import com.akshay.backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/story")
public class StoryController {

    private final StoryService storyService;
    private final UserService userService;

    @PostMapping("/create")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization")String token)
    {
        User user= userService.getUserFromToken(token);
        return storyService.createStory(story,user);
    }

    @GetMapping("/users/{userId}")
    public List<Story> findStoryByUserId(@PathVariable Integer userId)
    {
        return storyService.findStoryByUserId(userId);
    }

    @DeleteMapping("/delete/{storyId}")
    public String deleteStory(@PathVariable("storyId") Integer storyId, @RequestHeader("Authorization") String token) throws Exception {
        Integer UserId =userService.getUserFromToken(token).getId();
        return storyService.deleteStory(UserId,storyId);
    }

}
