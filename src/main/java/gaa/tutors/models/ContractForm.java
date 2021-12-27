package gaa.tutors.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contract_table")
@Data
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class ContractForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @Column
    private Long hours;

    @Column
    private Long total;

    public ContractForm(){}
    public ContractForm(User user, Tutor tutor, Long hours, Long total)
    {
        this.user = user;
        this.tutor = tutor;
        this.hours = hours;
        this.total = total;
    }
}