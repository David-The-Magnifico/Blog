package africa.semicolon.Blog.DTOs.response;

import lombok.Data;

@Data
public class RegisterUserResponse {
    private String id;
    private String username;
    private String dateTimeRegistered;
}
