package africa.semicolon.Blog.data.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Posts extends MongoRepository<Posts, String> {
}
