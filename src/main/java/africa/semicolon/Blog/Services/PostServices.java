package africa.semicolon.Blog.Services;

import africa.semicolon.Blog.DTOs.request.*;
import africa.semicolon.Blog.DTOs.response.*;
import africa.semicolon.Blog.data.Model.User;
import africa.semicolon.Blog.data.Model.Post;


public interface PostServices {
    Post createPostWith(CreatePostRequest createPostRequest);
    EditPostResponse editPostWith(EditPostRequest editPostRequest, Post authorPost);
    DeletePostResponse deletePostWith(DeletePostRequest deletePostRequest, Post authorPost);
    ViewPostResponse addViewWith(ViewPostRequest viewPostRequest, User viewer);
    CommentResponse addCommentWith(CommentRequest commentRequest, User commenter);
    ViewsCountResponse getNumberOfViews(ViewsCountRequest viewsCountRequest);
}
