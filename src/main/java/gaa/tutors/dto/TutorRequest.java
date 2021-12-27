package gaa.tutors.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutorRequest {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String subject;
    private Long cost;
    private float rate;

    public TutorRequest(Long id, String name, String surname, String email, String subject, Long cost, float rate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.subject = subject;
        this.cost = cost;
        this.rate = rate;
    }
}