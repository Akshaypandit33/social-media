package com.akshay.backend.ServiceImplementation;

import com.akshay.backend.Model.Story;
import com.akshay.backend.Model.User;
import com.akshay.backend.Repository.StoryRepository;
import com.akshay.backend.Service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoryServiceImplementation implements StoryService {
    private final StoryRepository storyRepository;


    @Override
    public Story createStory(Story story, User user) {
        Story newStory=  new Story();
        newStory.setContents(story.getContents());
        newStory.setUser(user);
        newStory.setCreatedAt(LocalDateTime.now());

        return storyRepository.save(newStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) {
        return storyRepository.findByUserId(userId);
    }

    @Override
    public String deleteStory(Integer userid, Integer storyId) throws Exception {
        Optional<Story> story= storyRepository.findById(storyId);
        if(story.isEmpty())
        {
            throw new Exception("Story not Exist");
        }
        if(Objects.equals(userid, story.get().getUser().getId()))
        {
            storyRepository.delete(story.get());
            return "Story Deleted Successfully";
        }
        return "You can not delete this  story";
    }
}
