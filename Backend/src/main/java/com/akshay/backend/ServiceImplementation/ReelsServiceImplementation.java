package com.akshay.backend.ServiceImplementation;

import com.akshay.backend.Model.Reels;
import com.akshay.backend.Model.User;
import com.akshay.backend.Repository.ReelsRepository;
import com.akshay.backend.Service.ReelsService;
import com.akshay.backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ReelsServiceImplementation implements ReelsService {

    private final UserService userService;
    private final ReelsRepository reelsRepository;
    @Override
    public Reels createReels(Reels reels, User user) {
        Reels newReel= new Reels();

        newReel.setTitle(reels.getTitle());
        newReel.setVideo(reels.getVideo());
        newReel.setCreatedAt(LocalDateTime.now());
        newReel.setUser(user);

        return reelsRepository.save(newReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUserReels(Integer userId) throws Exception {
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }

    @Override
    public String deleteReels(Integer userId, Integer reelsId) throws Exception {
        Reels reels=reelsRepository.findById(reelsId).orElseThrow(
                () -> new Exception("Reels not found")
        );
       if(Objects.equals(userId, reels.getUser().getId()))
       {
           reelsRepository.delete(reels);
           return "Reels Deleted Successfully";
       }
        return "Error reels can not be deleted ! Try again";
    }
}
