package africa.semicolon.Blog.Controllers;

import africa.semicolon.Blog.DTOs.request.*;
import africa.semicolon.Blog.DTOs.response.*;
import africa.semicolon.Blog.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            var result = userServices.register(registerRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            var result = userServices.login(loginRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest logoutRequest) {
        try {
            var result = userServices.logout(logoutRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequest createPostRequest) {
        try {
            var result = userServices.createPost(createPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PutMapping("/edit-post")
    public ResponseEntity<?> editPost(@RequestBody EditPostRequest editPostRequest) {
        try {
            var result = userServices.editPost(editPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-post")
    public ResponseEntity<?> deletePost(@RequestBody DeletePostRequest deletePostRequest) {
        try {
            var result = userServices.deletePost(deletePostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/view-post")
    public ResponseEntity<?> viewPost(@RequestBody ViewPostRequest viewPostRequest) {
        try {
            var result = userServices.viewPost(viewPostRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/comment")
    public ResponseEntity<?> addComment(@RequestBody CommentRequest commentRequest) {
        try {
            var result = userServices.reactToPost(commentRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @GetMapping("/view-posts")
    public ResponseEntity<?> getUserPosts(@RequestBody GetUserPostsRequest getUserPostsRequest) {
        try {
            var result = userServices.getUserPost(getUserPostsRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}
