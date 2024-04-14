package africa.semicolon.Blog.DTOs.request;

import lombok.Data;

@Data
public class CommentRequest {
    private String commenter;
    private String comment;
    private String PostId;


}
