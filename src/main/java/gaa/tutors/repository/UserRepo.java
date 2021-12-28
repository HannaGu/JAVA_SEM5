package gaa.tutors.repository;

import gaa.tutors.exceptions.RepositoryException;
import gaa.tutors.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    @Modifying
    void deleteById(Long id);
    User findByLogin(String login);
    List<User> findAll();
    boolean existsUserByLogin(String login);
    boolean existsUserByLoginAndPassword(String login, String password);
    User getById(Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.name=:name, u.surname=:surname, u.email=:email where u.id=:id")
    void updateUserById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("email") String email
    ) throws RepositoryException;

}
