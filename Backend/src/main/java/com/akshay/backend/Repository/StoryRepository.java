package com.akshay.backend.Repository;

import com.akshay.backend.Model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

    List<Story> findByUserId(Integer userId);
}
