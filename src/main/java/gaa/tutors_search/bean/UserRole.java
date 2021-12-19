package gaa.tutors_search.bean;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="role_table")
@Data
public class UserRole {
    public long getId() { return id; }

    public void setId(long id) { this.id = id;}

    public Role getName() { return name;}

    public void setName(Role name) {this.name = name;}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Role name;

}
