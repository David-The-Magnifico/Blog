package africa.semicolon.Blog.Utils;

import africa.semicolon.Blog.DTOs.request.LoginRequest;
import africa.semicolon.Blog.data.Model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class Cryptography {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean isMatches(LoginRequest loginRequest, User user) {
        String rawPassword = loginRequest.getPassword();
        String encodePassword = user.getPassword();
        return passwordEncoder.matches(rawPassword, encodePassword);
    }
}
