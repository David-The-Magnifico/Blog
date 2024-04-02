package africa.semicolon.Blog.data.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Admin")
public class Admin {
    @Id
    private String id;
    private String role;

    public Admin() {
        this.role = "admin";
    }

    public Admin(String id, String role) {
        this.id = id;
        this.role = role;
    }
}
