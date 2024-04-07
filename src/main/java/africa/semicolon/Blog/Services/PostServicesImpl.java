package africa.semicolon.Blog.Services;

import africa.semicolon.Blog.DTOs.request.*;
import africa.semicolon.Blog.DTOs.response.*;
import africa.semicolon.Blog.Exception.PostNotFoundException;
import africa.semicolon.Blog.data.Model.*;
import africa.semicolon.Blog.data.Repository.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static africa.semicolon.Blog.Utils.Mapper.*;

@Service
public class PostServicesImpl implements PostServices {
    @Autowired
    private Posts posts;
    @Autowired
    private ViewServices viewServices;
    @Autowired
    private CommentServices commentServices;

    @Override
    public Post createPostWith(CreatePostRequest createPostRequest) {
        Post newPost = map(createPostRequest);
        return posts.save(newPost);
    }

    @Override
    public EditPostResponse editPostWith(EditPostRequest editPostRequest, Post authorPost) {
        Post editedPost = map(editPostRequest, authorPost);
        posts.save(editedPost);
        return mapEditPostResponseWith(editedPost);
    }

    @Override
    public DeletePostResponse deletePostWith(DeletePostRequest deletePostRequest, Post authorPost) {
        posts.delete(authorPost);
        return mapDeletePostResponseWith(authorPost);
    }

    @Override
    public ViewPostResponse addViewWith(ViewPostRequest viewPostRequest, User viewer) {
        Post post = findPostBy(viewPostRequest.getPostId());
        View newView = viewServices.saveViewOf(viewer);
        post.getViews().add(newView);
        posts.save(post);
        return mapViewPostResponseWith(newView);
    }

    @Override
    public CommentResponse addCommentWith(CommentRequest commentRequest, User commenter) {
        Post post = findPostBy(commentRequest.getBlogPostId());
        View newView = viewServices.saveViewOf(commenter);
        Comment newComment = commentServices.addCommentWith(commentRequest, commenter);
        post.getViews().add(newView);
        post.getComments().add(newComment);
        posts.save(post);
        return mapCommentResponse(newComment);
    }

    @Override
    public ViewsCountResponse getNumberOfViews(ViewsCountRequest viewsCountRequest) {
        Post foundPost = findPostBy(viewsCountRequest.getPostId());
        Long viewsCount = (long) foundPost.getViews().size();
        return map(viewsCount, viewsCountRequest.getPostId());
    }

    private Post findPostBy(String id) {
        Optional<Post> foundPost = posts.findById(id);
        if (foundPost.isEmpty()) throw new PostNotFoundException("Post not found");
        return foundPost.get();
    }
}