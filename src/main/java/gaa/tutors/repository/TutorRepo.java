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
     Tutor getById(Long id);
     boolean existsById(Long id);

  @Modifying
  @Query("update Tutor t set t.rate=:rate where t.id=:id")
  void updateTutorRateById(
          @Param("id") Long id,
          @Param("rate") float rate
  ) throws RepositoryException;

     @Modifying
     @Query("update Tutor t set t.name=:name, t.surname=:surname, t.email=:email, t.subject=:subject, t.cost=:cost, t.rate=:rate where t.id=:id")
     void updateTutorById(
             @Param("id") Long id,
             @Param("name") String name,
             @Param("surname") String surname,
             @Param("email") String email,
             @Param("subject") String subject,
             @Param("cost") int cost,
             @Param("rate") float rate
     ) throws RepositoryException;
 }
