package gaa.tutors.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contract_table")
@Data
@Getter
@Setter
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

    public ContractForm(){}
    public ContractForm(User user, Tutor tutor, Long hours)
    {
        this.user = user;
        this.tutor = tutor;
        this.hours = hours;
    }
}