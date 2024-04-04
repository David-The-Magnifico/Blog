package africa.semicolon.Blog.data.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.africa.semicolon.Blog.data.Model.Post;

public interface Posts extends MongoRepository<Post, String> {
}
