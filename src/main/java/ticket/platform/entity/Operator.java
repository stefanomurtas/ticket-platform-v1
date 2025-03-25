package ticket.platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ticket.platform.enums.AbstractEntityStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "operators")
public class Operator extends AbstractEntityStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Email
    @NotBlank(message = "la mail non può essere vuota o null")
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @NotBlank(message = "username non puo essere vuoto o null")
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @NotBlank(message = "password non puo essere vuoto o null")
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "active", nullable = false)
    private Boolean active;

    public Operator() {
    }

    public Operator(LocalDateTime createdAt, LocalDateTime updatedAt, String email, String username, String password, Boolean active) {
        super(createdAt, updatedAt);
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public Boolean isActive() {
        return this.active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
