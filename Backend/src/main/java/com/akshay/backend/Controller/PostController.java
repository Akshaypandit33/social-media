package com.akshay.backend.Controller;

import com.akshay.backend.Model.Post;
import com.akshay.backend.Service.PostService;
import com.akshay.backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    private  final UserService userService;

    @PostMapping("/posts/user/")

    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String token,@RequestBody Post post) throws Exception {
        Integer userid= userService.getUserFromToken(token).getId();
        Post createdPost =postService.createPost(post,userid);
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postid}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postid, @RequestHeader("Authorization") String token) throws Exception {
       Integer userid=userService.getUserFromToken(token).getId();
        String message= postService.deletePost(postid,userid);
       return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost()
    {
        List<Post> post= postService.findAllPost();
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @GetMapping("/posts/{postid}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postid) throws Exception {
        Post post=postService.findPostById(postid);
        return new ResponseEntity<>(post,HttpStatus.FOUND);

    }

    @GetMapping("/posts/userPost")
    public ResponseEntity<List<Post>> findUserPost(@RequestHeader("Authorization") String token) throws Exception {
        Integer userid=userService.getUserFromToken(token).getId();
        List<Post> post= postService.findPostByUserId(userid);
        return new ResponseEntity<>(post,HttpStatus.FOUND);

    }
    @PutMapping("/posts/{postid}/save")
    public ResponseEntity<Post> savePost(@RequestHeader("Authorization") String token,@PathVariable Integer postid) throws Exception {
        Integer userid=userService.getUserFromToken(token).getId();
        Post post=postService.savedPost(postid,userid);
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/like/{postid}")
    public ResponseEntity<Post> likePost(@RequestHeader("Authorization") String token,@PathVariable Integer postid) throws Exception {
        Integer userid=userService.getUserFromToken(token).getId();
        Post post= postService.likePost(postid,userid);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @GetMapping("/posts/likes/{postid}")
    public ResponseEntity<Integer> totalLikes(@PathVariable Integer postid) throws Exception {
        Integer totalLike= postService.totalLikes(postid);
        return new ResponseEntity<>(totalLike,HttpStatus.OK);
    }
}
