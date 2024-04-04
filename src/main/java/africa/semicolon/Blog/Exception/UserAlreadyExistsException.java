package africa.semicolon.Blog.Exception;

public class UserAlreadyExistsException extends BlogThrowsException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
