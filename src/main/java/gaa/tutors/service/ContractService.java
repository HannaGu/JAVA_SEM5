package gaa.tutors.service;

import gaa.tutors.dto.ContractRequest;
import gaa.tutors.exceptions.ControllerException;
import gaa.tutors.exceptions.RepositoryException;
import gaa.tutors.models.ContractForm;
import gaa.tutors.models.Tutor;
import gaa.tutors.models.User;
import gaa.tutors.repository.ContractRepo;
import gaa.tutors.repository.TutorRepo;
import gaa.tutors.repository.UserRepo;
import gaa.tutors.service.interfaces.IContractService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

@Service
public class ContractService implements IContractService {

    @Autowired
    private ContractRepo contractRepository;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private TutorRepo tutorRepository;
    @Override
    public ContractForm createContract(ContractRequest contractRequest, String UserLogin) throws SerialException, ControllerException {

        User user = userRepository.findByLogin(UserLogin);
        Tutor tutor = tutorRepository.getById(contractRequest.tutor_id);
        ContractForm contract = new ContractForm(user, tutor, contractRequest.orderHours, contractRequest.totalCost);
        System.out.println(contract);
        return contractRepository.save(contract);
    }
    @Override
    public List<ContractForm> getAllByUserId(Long user_id)throws ServiceException {
        try {
            return contractRepository.getAllByUserId(user_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());

        }
    }
}
