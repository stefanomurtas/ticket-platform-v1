package ticket.platform.ticketplatform.entity;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import ticket.platform.ticketplatform.enums.AbstractEntityStatus;
import ticket.platform.ticketplatform.enums.TicketStatus;

@Entity
@Table(name = "tickets")
public class Ticket extends AbstractEntityStatus {
    @Autowired

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TicketStatus status;
    @Column(name = "title", nullable = false)
    @NotBlank(message = "Aggiungere titolo, non può essere vuoto")
    private String title;
    @Column(name = "details")
    @NotBlank(message = "Aggiungere dettagli per i problemi")
    private String details;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "operator_id", nullable = false)

    private Operator operator;

    public Long getId() {
        return id;
    }
    public Ticket() {

    }

    public Ticket(TicketStatus status, String details, String title, Category category, Operator operator, LocalDateTime createdAt,LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.status = status;
        this.details = details;
        this.title = title;
        this.category = category;
        this.operator = operator;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
