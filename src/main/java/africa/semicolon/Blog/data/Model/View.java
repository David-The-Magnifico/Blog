package africa.semicolon.Blog.data.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Document("Views")
public class View extends javax.swing.text.View {
    @Id
    private String id;
    @DBRef
    private User viewer;
    private LocalDateTime timeOfView = LocalDateTime.now();

    @Override
    public String toString() {
        String viewedTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z").format(timeOfView);
        return String.format("""
                id='%s'
                timeOfView='%s'""",
                id, viewedTime);
    }
}
