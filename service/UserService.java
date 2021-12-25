package gaa.tutors.service;

import gaa.tutors.models.Role;
import gaa.tutors.models.User;
import gaa.tutors.models.UserRole;
import gaa.tutors.repository.UserRepo;
import gaa.tutors.repository.UserRoleRepo;

import gaa.tutors.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private UserRoleRepo userRoleRepository;

    @Override
    public User saveUser(User user) {
        UserRole userRole = userRoleRepository.findByName(Role.ROLE_USER);
        user.setUserRole(userRole);
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean existsUserByLogin(String login) {
        return userRepository.existsUserByLogin(login);
    }

    @Override
    public boolean existsUserByLoginAndPassword(String login, String password) {
        return findByLoginAndPassword(login, password) != null;
    }
}