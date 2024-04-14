package africa.semicolon.Blog.DTOs.response;

import lombok.Data;

@Data
public class GetUserPostsResponse {
    private String id;
    private String username;
    private String userPosts;
}
