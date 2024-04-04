package africa.semicolon.Blog.DTOs.response;

import lombok.Data;

@Data
public class ViewsCountResponse {
    private String postId;
    private Long viewsCount;
}
