package ticket.platform.enums;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntityStatus {

    @PastOrPresent(message = "non può essere creata in futuro")
    private LocalDateTime createdAt;
    @PastOrPresent(message = "non può essere creata in futuro")
    private LocalDateTime updatedAt;

    public AbstractEntityStatus(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AbstractEntityStatus() {
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}