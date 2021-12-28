package gaa.tutors.controllers;

import gaa.tutors.config.MailSender;
import gaa.tutors.dto.ContractRequest;
import gaa.tutors.dto.ContractRequestNoId;
import gaa.tutors.dto.TutorRequest;
import gaa.tutors.exceptions.ControllerException;
import gaa.tutors.exceptions.RepositoryException;
import gaa.tutors.jwt.JwtFilter;
import gaa.tutors.jwt.JwtProvider;
import gaa.tutors.models.ContractForm;
import gaa.tutors.models.Tutor;
import gaa.tutors.repository.ContractRepo;
import gaa.tutors.service.ContractService;
import gaa.tutors.service.CustomUserDetailsService;
import gaa.tutors.service.TutorService;
import gaa.tutors.validator.ContractValidator;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.rowset.serial.SerialException;

@RestController
public class ContractRestController {
    @Autowired
    private TutorService tutorService;
    @Autowired
    private ContractValidator contractValidator;
    @Autowired
    private ContractService contractService;

    @Autowired
    private ContractRepo contractRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private MailSender mailSender;


    @PostMapping("/user/createContract")
    public ResponseEntity<?> createContract(@RequestBody ContractRequest contractRequest) throws ControllerException, SerialException {

        System.out.println(contractService.createContract(contractRequest, JwtFilter.getCurrentUserLogin()));

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/user/getAllByUserId/{id}")
    public ResponseEntity<?> getAllByUserId(@PathVariable(name = "id")Long id) throws ControllerException{
        try {
            return new ResponseEntity<>(contractService.getAllByUserId(id),HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @DeleteMapping("/admin/deleteContractByTutorId/{id}")
    public ResponseEntity<?> deleteContractByTutorId(@PathVariable(name="id")Long id)throws ControllerException {
        try {
            contractRepository.deleteByTutorId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException | RepositoryException e) {
            throw new ControllerException(e);

        }
    }

    @DeleteMapping("/admin/deleteContractByUserId/{id}")
    public ResponseEntity<?> deleteContractByUserId(@PathVariable(name="id")Long id)throws ControllerException {
        try {
            contractRepository.deleteByUserId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException | RepositoryException e) {
            throw new ControllerException(e);

        }
    }

}
