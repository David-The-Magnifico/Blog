package africa.semicolon.Blog.DTOs.request;

import lombok.Data;

@Data
public class EditPostRequest {
    private String postId;
    private String author;
    private String title;
    private String content;
}
