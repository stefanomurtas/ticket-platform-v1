package ticket.platform.entity;

import jakarta.persistence.*;
import ticket.platform.enums.AbstractEntityStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
public class Category extends AbstractEntityStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Category() {
    }

    public Category(LocalDateTime createdAt, LocalDateTime updatedAt, String name) {
        super(createdAt, updatedAt);
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}