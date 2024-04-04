package africa.semicolon.Blog.data.Repository;

import africa.semicolon.Blog.data.Model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Comments extends MongoRepository<Comment, String> {
}
