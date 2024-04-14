package africa.semicolon.Blog.Services;

import africa.semicolon.Blog.DTOs.request.*;
import africa.semicolon.Blog.DTOs.response.*;

public interface UserServices {
    RegisterUserResponse register(RegisterRequest registerRequest);

    LoginUserResponse login(LoginRequest loginRequest);

    LogoutUserResponse logout(LogoutRequest logoutRequest);

    CreatePostResponse createPost(CreatePostRequest createPostRequest);

    EditPostResponse editPost(EditPostRequest editPostRequest);

    DeletePostResponse deletePost(DeletePostRequest deletePostRequest);

    ViewPostResponse viewPost(ViewPostRequest viewsPostRequest);

    CommentResponse reactToPost(CommentRequest commentRequest);

    GetUserPostsResponse getUserPosts(GetUserPostsRequest getUserPostRequest);
}
