package com.akshay.backend.Service;

import com.akshay.backend.Model.Comments;

public interface CommentService {
    Comments createComment(Comments comments,
                           Integer postId,
                           Integer userId) throws Exception;
    Comments findCommentsById(Integer commentId) throws Exception;
    Comments likeComment(Integer commentId, Integer userId) throws Exception;

    Integer totalLikes(Integer commentId) throws Exception;
}
