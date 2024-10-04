package com.akshay.backend.Service;

import com.akshay.backend.Model.Story;
import com.akshay.backend.Model.User;

import java.util.List;

public interface StoryService {

    Story createStory(Story story,User user);

    List<Story> findStoryByUserId(Integer userId);

    String deleteStory(Integer userid, Integer storyId) throws Exception;

}
