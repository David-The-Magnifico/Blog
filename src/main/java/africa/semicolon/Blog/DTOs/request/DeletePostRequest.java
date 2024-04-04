package africa.semicolon.Blog.DTOs.request;

import lombok.Data;

@Data
public class DeletePostRequest {
    private String postId;
    private String author;
}
