package africa.semicolon.Blog.Controllers;

import africa.semicolon.Blog.DTOs.request.ViewsCountRequest;
import africa.semicolon.Blog.DTOs.response.ApiResponse;
import africa.semicolon.Blog.Services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static javax.security.auth.callback.ConfirmationCallback.OK;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/user")
public class PostController {
    @Autowired
    private PostServices postServices;

    @GetMapping("/number-of-views")
    public ResponseEntity<?> getViewsCount(@RequestBody ViewsCountRequest viewsCountRequest) {
        try {
                var result = postServices.getNumberOfViews(viewsCountRequest);
                return new ResponseEntity<>(new ApiResponse(true, result), HttpStatusCode.valueOf(OK));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
}
