package ticket.platform.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ticket.platform.enums.AbstractEntityStatus;

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

    
     @ManyToMany (fetch=FetchType.EAGER)
    @JoinTable(
        name="operator_role",
        joinColumns= @JoinColumn (name = "operator_id"),
        inverseJoinColumns= @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
    
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

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
