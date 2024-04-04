package africa.semicolon.Blog.DTOs.response;

import lombok.Data;

@Data
public class GetUserPostsResponse {
    private String userId;
    private String username;
    private String userPosts;
}
