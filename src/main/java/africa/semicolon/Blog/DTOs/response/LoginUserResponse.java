package africa.semicolon.Blog.DTOs.response;

import lombok.Data;

@Data
public class LoginUserResponse {
    private String id;
    private String username;
    private boolean isLoggedIn;
}
