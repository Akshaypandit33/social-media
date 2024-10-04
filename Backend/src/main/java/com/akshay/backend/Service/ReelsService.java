package com.akshay.backend.Service;

import com.akshay.backend.Model.Reels;
import com.akshay.backend.Model.User;

import java.util.List;

public interface ReelsService {

    Reels createReels(Reels reels, User user);

    List<Reels> findAllReels();

    List<Reels> findUserReels(Integer userId) throws Exception;
    String deleteReels(Integer userId,Integer reelsId) throws Exception;
}
