package africa.semicolon.Blog.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import africa.semicolon.Blog.data.Model.Post;

@Data
@AllArgsConstructor
public class UpdatePostRequest {
    private String postAuthor;
    private Post post;
}
