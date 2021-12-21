package gaa.tutors.repository;

import gaa.tutors.exceptions.RepositoryException;
import gaa.tutors.models.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

 @Repository
    public interface TutorRepo extends JpaRepository<Tutor, Long> {
     @Modifying
     void deleteById(int id) throws RepositoryException;

     @Modifying
     void deleteByLogin(String login) throws RepositoryException;

     Tutor getById(Long id);

     Tutor getByLogin(String login) throws RepositoryException;

     boolean existsByLogin(String login) throws RepositoryException;

     @Modifying
     @Query("update Tutor t set t.subject=:subject, t.cost=:cost where t.id=:id")
     void updateScooterById(
             @Param("id") Long id,
             @Param("subject") String subject,
             @Param("cost") int cost
     ) throws RepositoryException;
 }
