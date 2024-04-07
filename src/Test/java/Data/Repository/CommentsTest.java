package Data.Repository;

import africa.semicolon.Blog.data.Model.Comment;
import africa.semicolon.Blog.data.Repository.Comments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@DataMongoTest
public class CommentsTest {
    @Autowired
    private Comments comments;

    @Test
    public void commentsTest(){
        assertThat(comments.count(), is(0L));
        Comment newComment = new Comment();
        var savedComment = comments.save(newComment);
        assertThat(comments.count(), is(1L));
        assertThat(savedComment.getId(), notNullValue());
    }

}
