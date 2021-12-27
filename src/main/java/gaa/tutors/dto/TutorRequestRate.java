package gaa.tutors.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutorRequestRate {

    private Long id;
    private float rate;

    public TutorRequestRate( Long id, float rate) {
        this.id = id;
        this.rate = rate;
    }
}