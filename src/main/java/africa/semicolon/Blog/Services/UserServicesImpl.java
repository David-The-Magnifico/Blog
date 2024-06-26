package africa.semicolon.Blog.Services;

import africa.semicolon.Blog.DTOs.request.*;
import africa.semicolon.Blog.DTOs.response.*;
import africa.semicolon.Blog.Exception.*;
import africa.semicolon.Blog.data.Model.*;
import africa.semicolon.Blog.data.Repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.Blog.Utils.Cleaner.cleanup;
import static africa.semicolon.Blog.Utils.Cryptography.*;
import static africa.semicolon.Blog.Utils.Mapper.*;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private Users users;
    @Autowired
    private PostServices postServices;

    @Override
    public RegisterUserResponse register(RegisterRequest registerRequest) {
        validate(registerRequest);
        registerRequest.setUsername(cleanup(registerRequest.getUsername()));
        registerRequest.setPassword(encode(registerRequest.getPassword()));
        User newUser = map(registerRequest);
        User savedUser = users.save(newUser);
        return mapRegisterResponseWith(savedUser);
    }

    @Override
    public LoginUserResponse login(LoginRequest loginRequest) {
        User foundUser = findUserBy(loginRequest.getUsername());
        if (!isMatches(loginRequest, foundUser)) throw new IncorrectPasswordException("Password is not correct");
        foundUser.setLoggedIn(true);
        User savedUser = users.save(foundUser);
        return mapLoginResponseWith(savedUser);
    }


    @Override
    public LogoutUserResponse logout(LogoutRequest logOutRequest) {
        User foundUser = findUserBy(logOutRequest.getUsername());
        foundUser.setLoggedIn(false);
        User savedUser = users.save(foundUser);
        return mapLogoutResponseWith(savedUser);
    }
    @Override
    public CreatePostResponse createPost(CreatePostRequest createPostRequest) {
        User foundUser = findUserBy(createPostRequest.getUsername());
        validateLoginStatusOf(foundUser);
        Post newPost = postServices.createPostWith(createPostRequest);
        foundUser.getPosts().add(newPost);
        users.save(foundUser);
        return mapCreatePostResponseWith(newPost);
    }

    @Override
    public EditPostResponse editPost(EditPostRequest editPostRequest) {
        return null;
    }

    @Override
    public DeletePostResponse deletePost(DeletePostRequest deletePostRequest) {
        return null;
    }

    @Override
    public EditPostResponse editPostWith(EditPostRequest editPostRequest) {
        User author = findUserBy(editPostRequest.getAuthor());
        validateLoginStatusOf(author);
        Post authorPost = findUserPostBy(editPostRequest.getPostId(), author);
        return postServices.editPostWith(editPostRequest, authorPost);
    }

    @Override
    public DeletePostResponse deletePostWith(DeletePostRequest deletePostRequest) {
        User author = findUserBy(deletePostRequest.getAuthor());
        validateLoginStatusOf(author);
        Post authorPost = findUserPostBy(deletePostRequest.getPostId(), author);
        return postServices.deletePostWith(deletePostRequest, authorPost);
    }

    @Override
    public ViewPostResponse viewPost(ViewPostRequest viewPostRequest) {
        if (viewPostRequest.getViewer() == null) return postServices.addViewWith(viewPostRequest, findUserBy("1092abanonymoususer"));
        User viewer = findUserBy(viewPostRequest.getViewer());
        return postServices.addViewWith(viewPostRequest, viewer);
    }

    @Override
    public CommentResponse reactToPost(CommentRequest commentRequest) {
        User commenter = findUserBy(commentRequest.getCommenter());
        validateLoginStatusOf(commenter);
        return postServices.addCommentWith(commentRequest, commenter);
    }

    @Override
    public GetUserPostsResponse getUserPosts(GetUserPostsRequest getUserPostsRequest) {
        User foundUser = findUserBy(getUserPostsRequest.getUsername());
        return mapGetUserPostsResponse(foundUser);
    }

    private void validateLoginStatusOf(User user) {
        if (!user.isLoggedIn()) throw new IllegalUserStateException("User is not logged in");
    }

    private Post findUserPostBy(String postId, User author) {
        for (Post post : author.getPosts()) if (post.getId().equals(postId)) return post;
        throw new PostNotFoundException("Post not found");
    }

    private User findUserBy(String username) {
        username = cleanup(username);
        User foundUser = users.findByUsername(username);
        if (foundUser == null) throw new UsernameNotFoundException(String.format("%s not found", username));
        return foundUser;
    }

    private void validateUniqueUsername(RegisterRequest registerRequest) {
        String username = cleanup(registerRequest.getUsername());
        boolean userExists = users.existsByUsername(username);
        if (userExists) throw new UserAlreadyExistsException(String.format("%s already exists", username));
    }

    private static void validateBlank(RegisterRequest registerRequest) {
        boolean isBlank = registerRequest.getUsername().isBlank() || registerRequest.getPassword().isBlank();
        if (isBlank) throw new InvalidArgumentException("Registration details cannot be blank");
    }

    private static void validateNull(RegisterRequest registerRequest) {
        boolean isNull = registerRequest.getUsername() == null || registerRequest.getPassword() == null;
        if (isNull) throw new InvalidArgumentException("Registration details are required");
    }

    private void validate(RegisterRequest registerRequest) {
        validateNull(registerRequest);
        validateBlank(registerRequest);
        validateUniqueUsername(registerRequest);
    }
}