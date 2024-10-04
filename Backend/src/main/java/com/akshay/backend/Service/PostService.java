package com.akshay.backend.Service;


import com.akshay.backend.Model.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post, Integer userid) throws Exception;
    String deletePost(Integer postid, Integer userid) throws Exception;

    List<Post> findPostByUserId(Integer userid) throws Exception;

    Post findPostById(Integer postid) throws Exception;
    List<Post> findAllPost();
    Post savedPost(Integer postid,Integer userid) throws Exception;
    Post likePost(Integer postid,Integer userid) throws Exception;
    Integer totalLikes(Integer postid) throws Exception;


}
