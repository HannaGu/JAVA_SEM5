package gaa.tutors.dto;

import gaa.tutors.models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String login;
    private Role role;
    private String name;
    private String surname;
    private String email;

    public UserResponse(Long id, String login, Role role, String name, String surname, String email) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }


}