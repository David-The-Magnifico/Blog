package africa.semicolon.Blog.Services;

import africa.semicolon.Blog.DTOs.request.CommentRequest;
import africa.semicolon.Blog.data.Model.Comment;
import africa.semicolon.Blog.data.Model.User;
import africa.semicolon.Blog.data.Repository.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.Blog.Utils.Mapper.map;

@Service
public class CommentServicesImpl implements CommentServices {
    @Autowired
    private Comments comments;

    @Override
    public Comment addComment(CommentRequest createCommentRequest) {
        Comment newComment = new Comment(createCommentRequest)map(createCommentRequest);
        return comments.save(newComment);
    }

    @Override
    public Comment addCommentWith(CommentRequest commentRequest, User commenter) {
        Comment newComment = map(commentRequest, commenter);
        return comments.save(newComment);
    }
}
