package gaa.tutors_search.bean;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user_tutor_table")
@Data
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

    public ContractForm() {
    }

    public ContractForm(User user, Tutor tutor) {
        this.user = user;
        this.tutor = tutor;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public Tutor getTutor() {return tutor;}
    public void setTutor(Tutor tutor) {this.tutor = tutor;}
}
