package africa.semicolon.Blog.data.Repository;

import africa.semicolon.Blog.data.Model.View;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Views extends MongoRepository<View, String> {
}
