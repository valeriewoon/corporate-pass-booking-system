package project.entity;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Data // to allow class to use Lombok. Defines getters, setters, hashcode, toString and equals
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="loan")
public class Loan {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne(
        fetch = FetchType.LAZY
    )
    // Define foreign key column name created in Loan as "staff_id"
    @JoinColumn(name= "staff_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "loans"})
    private Staff staff;
    private String loanDate;
    private String attraction;

    // Specify a many to many relationship
    @ManyToMany(
        cascade=CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    // JPA will automatically create a new association table
    // Specify the details of an association table called loan_pass
    // Many to Many: Loan_Pass will contain loanId which is a FK to loan table
    // And passId which is a FK to pass table
    @JoinTable(
        name="loan_pass",
        joinColumns=@JoinColumn(name="loan_id"),
        inverseJoinColumns=@JoinColumn(name="pass_id")
    )
    // Many to Many rs requires use of set
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "loans"}) 
    private Set<Pass> passList = new HashSet<Pass>();
    
    private String loanStatus;

    // Helper methods to update both ends of the bidirectional many-to-many relationship
    // Between loan and pass
    public void addPass(Pass pass) {
        this.passList.add(pass);
        pass.getLoans().add(this);
    }

    public void removePass(Pass pass) {
        this.passList.remove(pass);
        pass.getLoans().remove(this);
    }
    
}