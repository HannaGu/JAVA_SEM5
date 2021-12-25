package gaa.tutors.repository;

import gaa.tutors.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByLogin(String login);
    List<User> findAll();
    boolean existsUserByLogin(String login);
    boolean existsUserByLoginAndPassword(String login, String password);
    User getById(Long id);
}
