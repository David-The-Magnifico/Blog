package africa.semicolon.Blog.DTOs.response;

import lombok.Data;

@Data
public class ViewPostResponse {
    private String viewerId;
    private String viewer;
    private String timeOfView;
}
