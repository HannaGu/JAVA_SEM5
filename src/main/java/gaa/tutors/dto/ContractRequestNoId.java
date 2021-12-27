package gaa.tutors.dto;

import gaa.tutors.models.Tutor;
import gaa.tutors.models.User;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
public class ContractRequestNoId {
    @NotNull
    private User user;
    @NotNull
    @Size(min = 0 , message = "Количество часов должно быть больше 0")
    private Long hours;
    @NotNull
    private Tutor tutor;
}
