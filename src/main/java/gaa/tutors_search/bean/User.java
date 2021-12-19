package gaa.tutors_search.bean;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_table")
@Data
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column
    @Size(min=5, max=20, message="Login length should be between 5 and 20")
    @NotNull(message="Cannot be null")
    private String login;

    @Column
    @Size(min=5, max=20, message="Login length should be between 5 and 20")
    @NotNull(message="Cannot be null")
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id")
    private UserRole userRole;

    @Column
    @NotNull(message="Cannot be null")
    private String name;

    @Column
    @NotNull(message="Cannot be null")
    private String surname;

    @Column
    private boolean active;
    @Column
    private String email;
    @Column
    private String activationCode;


    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public UserRole getUserRole() {return userRole;}

    public void setUserRole(UserRole userRole) {this.userRole = userRole;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getActivationCode() {return activationCode;}

    public void setActivationCode(String activationCode) {this.activationCode = activationCode;}

    public boolean isActive() {return active;}

    public void setActive(boolean active) {this.active = active;}
}
