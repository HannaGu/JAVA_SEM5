package gaa.tutors_search.repository;

import gaa.tutors_search.bean.Role;
import gaa.tutors_search.bean.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    }
