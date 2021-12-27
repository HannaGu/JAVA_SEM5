package gaa.tutors.service;
import gaa.tutors.exceptions.RepositoryException;
import gaa.tutors.models.Tutor;
import gaa.tutors.repository.TutorRepo;
import gaa.tutors.service.interfaces.ITutorService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService implements ITutorService {

    @Autowired
    private TutorRepo tutorRepository;

    @Override
    public void deleteById(Long id) {
        tutorRepository.deleteById(id);
    }

    @Override
    public Tutor create(Tutor tutor)throws ServiceException {
        return tutorRepository.save(tutor);
    }

    @Override
    public boolean existsById(Long id) throws ServiceException {
        try {
            return tutorRepository.existsById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Tutor> getAll()throws ServiceException {
        return tutorRepository.findAll();
    }

    @Override
    public Tutor getById(Long id)throws ServiceException {
        try {
            return tutorRepository.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

      @Override
    public void updateTutorById( Long id, String name, String surname, String email, String subject, Long cost, float rate) throws ServiceException{
        try {
            tutorRepository.updateTutorById(id, name, surname, email, subject, cost, rate);
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage());

        }
    }

    @Override
    public void updateTutorRateById( Long id, float rate) throws ServiceException{
        try {
            tutorRepository.updateTutorRateById(id, rate);
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage());

        }
    }
}