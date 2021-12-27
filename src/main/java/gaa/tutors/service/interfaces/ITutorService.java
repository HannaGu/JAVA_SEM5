package gaa.tutors.service.interfaces;

import gaa.tutors.models.Tutor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ITutorService {
    @Transactional
    void deleteById(Long id) throws ServiceException;

    Tutor create(Tutor tutorOffer)throws ServiceException;

    boolean existsById(Long id) throws ServiceException;

    List<Tutor> getAll()throws ServiceException;

    Tutor getById(Long id)throws ServiceException;

    @Transactional
    void updateTutorById(
            Long id,
            String name,
            String surname,
            String email,
            String subject,
            Long cost,
            float rate)throws ServiceException;
    @Transactional
    void updateTutorRateById(Long id, float rate)throws ServiceException;
}