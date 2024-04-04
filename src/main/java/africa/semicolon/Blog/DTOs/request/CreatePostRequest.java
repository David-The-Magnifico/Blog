package africa.semicolon.Blog.DTOs.request;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String username;
    private String title;
    private String content;
}
