package africa.semicolon.Blog.Services;

import africa.semicolon.Blog.DTOs.request.CommentRequest;
import africa.semicolon.Blog.data.Model.Comment;
import africa.semicolon.Blog.data.Model.User;

public interface CommentServices {
    Comment addComment(CommentRequest commentRequest, User commenter);

    Comment addCommentWith(CommentRequest commentRequest, User commenter);
}
