package africa.semicolon.Blog.DTOs.response;

import lombok.Data;

@Data
public class EditPostResponse {
    public String id;
    public String title;
    public String content;
    public String dateCreated;


}
