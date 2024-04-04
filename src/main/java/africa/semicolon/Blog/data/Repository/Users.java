package africa.semicolon.Blog.data.Repository;

import africa.semicolon.Blog.data.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Users extends MongoRepository<User, String> {
    boolean existsByUsername(String username);
    User findByUsername(String username);
}
