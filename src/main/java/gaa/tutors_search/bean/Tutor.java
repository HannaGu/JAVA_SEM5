package gaa.tutors_search.bean;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="tutor_table")
@Data
public class Tutor {
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
    @NotNull(message="Cannot be null")
    private String subject;

    @Column
    @NotNull(message="Cannot be null")
    @Min(1)
    private int costPerHour;

    @Column
    private boolean active;
    @Column
    private String email;
    @Column
    private String activationCode;

    public Tutor() {
    }

    public Tutor(String name, String surname, String subject, int costPerHour) {
        this.name = name;
        this.surname = surname;
        this.subject = subject;
        this.costPerHour = costPerHour;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public UserRole getUserRole() {return userRole;}
    public void setUserRole(UserRole userRole) {this.userRole = userRole;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getSurname() {return surname;}
    public void setSurname(String surname) {this.surname = surname;}
    public String getSubject() {return subject;}
    public void setSubject(String subject) {this.subject = subject;}
    public int getCostPerHour() {return costPerHour;}
    public void setCostPerHour(int costPerHour) {this.costPerHour = costPerHour;}
    public boolean isActive() {return active;}
    public void setActive(boolean active) {this.active = active;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getActivationCode() {return activationCode;}
    public void setActivationCode(String activationCode) {this.activationCode = activationCode;}

}
