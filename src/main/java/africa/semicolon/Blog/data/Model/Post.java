package africa.semicolon.Blog.data.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.swing.text.View;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("Posts")
public class Post {
    @Id
    private String id;
    private String title;
    private String content;
    private LocalDateTime dateCreated = LocalDateTime.now();
    @DBRef
    private List<Comment> comments = new ArrayList<>();
    @DBRef
    private List<View> views = new ArrayList<>();

    @Override
    public String toString() {
        String createdAt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z").format(dateCreated);
        return String.format("""
                        id: %s
                        title: %s
                        content: %s
                        dateCreated: %s
                        comments: %s
                        views: %s""",
                        id,
                        title,
                        content,
                        createdAt,
                        comments,
                        views);
    }
}
