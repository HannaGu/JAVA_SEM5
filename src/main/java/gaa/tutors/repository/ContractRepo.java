package gaa.tutors.repository;

import gaa.tutors.exceptions.RepositoryException;
import gaa.tutors.models.ContractForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ContractRepo extends JpaRepository<ContractForm, Long> {
    @Modifying
    void deleteById(Long id);
    @Modifying
    @Transactional
    @Query("delete from ContractForm c where c.tutor.id=:tutor_id")
    void deleteByTutorId(@Param("tutor_id") Long id) throws RepositoryException;
    @Modifying
    @Transactional
    @Query("delete from ContractForm c where c.user.id=:user_id")
    void deleteByUserId(@Param("user_id") Long id) throws RepositoryException;


    ContractForm getById(Long id);
    List<ContractForm> getAllByUserId(Long user_id)throws RepositoryException;

    boolean existsByTutorId(Long tutor_id)throws RepositoryException;
    boolean existsByUserId(Long user_id)throws RepositoryException;

}
