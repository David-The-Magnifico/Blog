package africa.semicolon.Blog.DTOs.response;

import lombok.Data;

@Data
public class LogoutUserResponse {
    private String id;
    private String username;
    private boolean isLoggedIn;
}
