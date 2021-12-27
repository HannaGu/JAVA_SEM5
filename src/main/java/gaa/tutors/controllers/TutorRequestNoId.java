package gaa.tutors.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import gaa.tutors.models.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;
@Getter
@Setter
public class TutorRequestNoId {

    private String name;
    private String surname;
    private String email;
    private String subject;
    private Long cost;
    private float rate;

    public TutorRequestNoId( String name, String surname, String email, String subject, Long cost, float rate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.subject = subject;
        this.cost = cost;
        this.rate = rate;
    }
}