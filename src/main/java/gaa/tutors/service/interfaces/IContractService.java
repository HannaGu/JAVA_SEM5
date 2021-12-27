package gaa.tutors.service.interfaces;

import gaa.tutors.dto.ContractRequest;
import gaa.tutors.exceptions.ControllerException;
import gaa.tutors.models.ContractForm;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

@Service
public interface IContractService {
    ContractForm createContract(ContractRequest contractRequest, String UserLogin) throws SerialException, ControllerException;
    List<ContractForm> getAllByUserId(Long user_id)throws ServiceException, ServiceException;

}
