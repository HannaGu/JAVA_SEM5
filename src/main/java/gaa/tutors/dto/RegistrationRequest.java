package gaa.tutors.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
//import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
public class RegistrationRequest {
   private String login;
   private String password;
   private String email;
   private String name;
   private String surname;
}