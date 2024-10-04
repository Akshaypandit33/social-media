package com.akshay.backend.ServiceImplementation;

import com.akshay.backend.Model.Post;
import com.akshay.backend.Model.User;
import com.akshay.backend.Repository.PostRepository;
import com.akshay.backend.Repository.UserRepository;
import com.akshay.backend.Service.PostService;
import com.akshay.backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImplementation implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Post createPost(Post post, Integer userid) throws Exception {
        User user = userService.findUserById(userid);
        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImg(post.getImg());
        newPost.setVideo(post.getVideo());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(user);
        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postid, Integer userid) throws Exception {
        Post post = findPostById(postid);
        User user = userService.findUserById(userid);

        if (!post.getUser().getId().equals(user.getId())) {
            throw new Exception("You cannot delete this post");
        }
        postRepository.delete(post);
        return "Post Deleted";
    }

    @Override
    public List<Post> findPostByUserId(Integer userid) throws Exception {
        User user = userService.findUserById(userid);
        return postRepository.findPostByUserId(user.getId());
    }

    @Override
    public Post findPostById(Integer postid) throws Exception {
        return postRepository.findById(postid)
                .orElseThrow(() -> new Exception("Post Not Found"));
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postid, Integer userid) throws Exception {
        Post post = findPostById(postid);
        User user = userService.findUserById(userid);
            if (user.getSavedPost().contains(post)) {
                user.getSavedPost().remove(post);
            } else {
                user.getSavedPost().add(post);
            }


        // Save user to persist changes
        userRepository.save(user);

        // Save post if necessary
        return postRepository.save(post);
    }



    @Override
    public Post likePost(Integer postid, Integer userid) throws Exception {
        Post post = findPostById(postid);
        User user = userService.findUserById(userid);

        if (post.getLikes().contains(user)) {
            post.getLikes().remove(user);
        } else {
            post.getLikes().add(user);
        }

        // Save post to persist likes changes
        return postRepository.save(post);
    }



    @Override
    public Integer totalLikes(Integer postid) throws Exception {
        Post post = findPostById(postid);
        return post.getLikes() != null ? post.getLikes().size() : 0;
    }
}
