package Services;


import africa.semicolon.Blog.DTOs.request.*;
import africa.semicolon.Blog.Exception.*;
import africa.semicolon.Blog.Services.UserServices;
import africa.semicolon.Blog.data.Repository.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
@SpringBootTest
public class UserServicesTest {
    @Autowired
    private UserServices userServices;
    @Autowired
    private Users users;
    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private CreatePostRequest createPostRequest;
    private EditPostRequest editPostRequest;
    private DeletePostRequest deletePostRequest;

    @BeforeEach
    public void setUp() {
        users.deleteAll();

        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");

        loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");

        createPostRequest = new CreatePostRequest();
        createPostRequest.setUsername("username");
        createPostRequest.setTitle("title");
        createPostRequest.setContent("content");

        editPostRequest = new EditPostRequest();
        editPostRequest.setAuthor("username");
        editPostRequest.setTitle("title");
        editPostRequest.setContent("newContent");

        deletePostRequest = new DeletePostRequest();
        deletePostRequest.setAuthor("username");
    }

    @Test
    public void registerUser_numberOfUsersIsOneTest() {
        assertThat(users.count(), is(0L));
        var registerResponse = userServices.register(registerRequest);
        assertThat(users.count(), is(1L));
        assertThat(registerResponse.getId(), notNullValue());
    }

    @Test
    public void registerSameUser_throwsUserExistsExceptionTest() {
        userServices.register(registerRequest);
        try {
            userServices.register(registerRequest);
        }
        catch (UserAlreadyExistsException e) {
            assertThat(e.getMessage(), containsString("username already exists"));
        }
        assertThat(users.count(), is(1L));
    }

    @Test
    public void loginUserWithCorrectPassword_loginUserResponseIsNotNull() {
        userServices.register(registerRequest);
        assertThat(users.count(), is(1L));
        var loginResponse = userServices.login(loginRequest);
        assertThat(loginResponse.getId(), notNullValue());
    }

    @Test
    public void loginNonExistentUser_throwsUsernameNotFoundExceptionTest() {
        userServices.register(registerRequest);
        loginRequest.setUsername("Non existent username");
        try {
            userServices.login(loginRequest);
        }
        catch (UsernameNotFoundException e) {
            assertThat(e.getMessage(), containsString("non existent username not found"));
        }
    }

    @Test
    public void loginWithIncorrectPassword_throwsIncorrectPasswordExceptionTest() {
        userServices.register(registerRequest);
        loginRequest.setPassword("incorrectPassword");
        try {
            userServices.login(loginRequest);
        }
        catch (IncorrectPasswordException e) {
            assertThat(e.getMessage(), containsString("Password is not correct"));
        }
    }

    @Test
    public void userCreatesPost_numberOfUserPostsIsOneTest() {
        userServices.register(registerRequest);
        var foundUser = users.findByUsername(registerRequest.getUsername().toLowerCase());
        assertThat(foundUser.getPosts().size(), is(0));
        var createPostResponse = userServices.createPost(createPostRequest);
        foundUser = users.findByUsername(registerRequest.getUsername().toLowerCase());
        assertThat(foundUser.getPosts().size(), is(1));
        assertThat(createPostResponse.getPostId(), notNullValue());
    }

    @Test
    public void userEditsCreatedPost_postContentIsNewContent() {
        userServices.register(registerRequest);
        userServices.createPost(createPostRequest);
        var foundUser = users.findByUsername(registerRequest.getUsername().toLowerCase());
        var savedPost = foundUser.getPosts().getFirst();
        assertThat(foundUser.getPosts().size(), is(1));
        assertThat(savedPost.getContent(), containsString("content"));

        editPostRequest.setPostId(savedPost.getId());
        var editPostResponse = userServices.editPost(editPostRequest);
        foundUser = users.findByUsername(registerRequest.getUsername().toLowerCase());
        savedPost = foundUser.getPosts().getFirst();
        assertThat(foundUser.getPosts().size(), is(1));
        assertThat(savedPost.getContent(), containsString("newContent"));
        assertThat(editPostResponse.getId(), notNullValue());
    }

    @Test
    public void userDeletesCreatedPost_numberOfPostsIs0Test() {
        userServices.register(registerRequest);
        userServices.createPost(createPostRequest);
        var foundUser = users.findByUsername(registerRequest.getUsername().toLowerCase());
        var savedPost = foundUser.getPosts().getFirst();
        assertThat(foundUser.getPosts().size(), is(1));

        deletePostRequest.setPostId(savedPost.getId());
        var deletePostResponse = userServices.deletePost(deletePostRequest);
        foundUser = users.findByUsername(registerRequest.getUsername().toLowerCase());
        assertThat(deletePostResponse.getId(), notNullValue());
    }

    @Test
    public void testGetUserPosts() {
        userServices.register(registerRequest);
        userServices.createPost(createPostRequest);
        userServices.createPost(createPostRequest);
        GetUserPostsRequest getUserPostsRequest = new GetUserPostsRequest();
        getUserPostsRequest.setUsername("username");

        var getUserPostResponse = userServices.getUserPosts(getUserPostsRequest);
        assertThat(getUserPostResponse.getId(), notNullValue());
    }
}
