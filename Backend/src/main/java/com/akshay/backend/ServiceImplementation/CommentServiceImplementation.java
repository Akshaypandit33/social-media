package com.akshay.backend.ServiceImplementation;

import com.akshay.backend.Model.Comments;
import com.akshay.backend.Model.Post;
import com.akshay.backend.Model.User;
import com.akshay.backend.Repository.CommentRepository;
import com.akshay.backend.Repository.PostRepository;
import com.akshay.backend.Service.CommentService;
import com.akshay.backend.Service.PostService;
import com.akshay.backend.Service.UserService;;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImplementation implements CommentService {

    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    @Override
    public Comments createComment(Comments comments, Integer postId, Integer userId) throws Exception {
        User user= userService.findUserById(userId);
        Post post=postService.findPostById(postId);
        Comments comment = new Comments();
        comment.setUser(user);
        comment.setContent(comments.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        post.getComments().add(comment);
        postRepository.save(post);
        return comment;
    }

    @Override
    public Comments findCommentsById(Integer commentId) throws Exception {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new Exception("Comment with ID " + commentId + " not found")
        );
    }

    @Override
    public Comments likeComment(Integer commentId, Integer userId) throws Exception {
        Comments comment= findCommentsById(commentId);
        User user= userService.findUserById(userId);

        if(!comment.getLiked().contains(user))
        {
            comment.getLiked().add(user);
        }
        else {
            comment.getLiked().remove(user);
        }

        return  commentRepository.save(comment);
    }

    @Override
    public Integer totalLikes(Integer commentId) throws Exception {
        Comments comments=commentRepository.findById(commentId).orElseThrow(
                () -> new Exception("Comment not found")
        );
        return comments.getLiked() !=null ? comments.getLiked().size() : 0;
    }
}
