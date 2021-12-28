package gaa.tutors.service.interfaces;

import gaa.tutors.models.User;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IUserService{
    void deleteById(Long id);
    User getById(Long id);
    User saveUser(User user);
    User findByLogin(String login);
    List<User> findAll();
    User findById(Long id);
    User findByLoginAndPassword(String login, String password);
    boolean existsUserByLogin(String login);
    boolean existsUserByLoginAndPassword(String login, String password);

    @Transactional
    void updateUserById(
            Long id,
            String name,
            String surname,
            String email
            )throws ServiceException;
}
