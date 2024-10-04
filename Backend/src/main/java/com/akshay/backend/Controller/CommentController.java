package com.akshay.backend.Controller;

import com.akshay.backend.Model.Comments;
import com.akshay.backend.Service.CommentService;
import com.akshay.backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/posts/{postId}")
    public Comments createComment(@RequestBody Comments comments,
                                  @RequestHeader("Authorization") String token,
                                  @PathVariable("postId") Integer postId) throws Exception {
        Integer userId= userService.getUserFromToken(token).getId();

        return commentService.createComment(comments,postId,userId);
    }

    @PutMapping("/like/{commentId}")
    public Comments likeComments(@RequestHeader("Authorization") String token, @PathVariable Integer commentId) throws Exception {
        Integer userId= userService.getUserFromToken(token).getId();
        return commentService.likeComment(commentId,userId);

    }

    @GetMapping("/like/{commentId}")
    public Integer totalLikes(@PathVariable Integer commentId) throws Exception {
        return commentService.totalLikes(commentId);
    }

}
