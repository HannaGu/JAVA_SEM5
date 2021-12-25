package gaa.tutors.repository;

import gaa.tutors.models.Role;
import gaa.tutors.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
    UserRole findByName(Role name);
}