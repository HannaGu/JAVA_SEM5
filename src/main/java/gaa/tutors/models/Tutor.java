package gaa.tutors.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String subject;

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