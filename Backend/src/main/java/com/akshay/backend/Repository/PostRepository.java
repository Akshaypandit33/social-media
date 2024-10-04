package com.akshay.backend.Repository;


import com.akshay.backend.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("SELECT p from Post p where p.user.id=:userid")
    List<Post> findPostByUserId(Integer userid);
}
