package gaa.tutors.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tutor_table")
@Data
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@NotNull
    @Column
    private String name;
    @NotNull
    @Column
    private String surname;
    @NotNull
    @Column
    private String email;
    @NotNull
    @Column
    private String subject;
    @Min(0)
    @NotNull
    @Column
    private Long cost;

    @Column
    private float rate;

    public Tutor(){}
    public Tutor( String name, String surname, String email, String subject, Long cost, float rate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.subject = subject;
        this.cost = cost;
        this.rate=rate;
    }
}