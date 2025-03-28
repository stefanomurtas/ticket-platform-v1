package ticket.platform.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name non puo essere vuoto o null")
    private String name;
    
    
    @ManyToMany (mappedBy = "roles", fetch = FetchType.EAGER)
   @JsonBackReference
    private Set<Operator> operators;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Operator> getOperators() {
        return this.operators;
    }

    public void setOpeators(Set<Operator> operators) {
        this.operators = operators;
    }
    
}