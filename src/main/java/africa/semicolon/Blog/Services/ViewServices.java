package africa.semicolon.Blog.Services;

import africa.semicolon.Blog.data.Model.User;
import africa.semicolon.Blog.data.Model.View;

public interface ViewServices {
    View saveViewOf(User viewer);
}
