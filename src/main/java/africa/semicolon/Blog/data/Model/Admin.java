package africa.semicolon.Blog.data.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Admin")
public class Admin {
    @Id
    private String adminId;
    private String role;

    public Admin() {
        this.role = "admin";
    }

    public Admin(String adminId, String role) {
        this.adminId = adminId;
        this.role = role;
    }
}
