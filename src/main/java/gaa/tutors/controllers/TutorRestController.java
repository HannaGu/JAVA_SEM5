package gaa.tutors.controllers;

import gaa.tutors.config.MailSender;
import gaa.tutors.dto.ContractRequest;
import gaa.tutors.dto.TutorRequest;
import gaa.tutors.dto.TutorRequestRate;
import gaa.tutors.exceptions.ControllerException;
import gaa.tutors.exceptions.RepositoryException;
import gaa.tutors.jwt.JwtFilter;
import gaa.tutors.models.Tutor;
import gaa.tutors.models.User;
import gaa.tutors.repository.ContractRepo;
import gaa.tutors.service.TutorService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.rowset.serial.SerialException;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class TutorRestController {
    @Autowired
    private TutorService tutorService;

    @Autowired
    private ContractRepo contractRepository;

    @Autowired
    private MailSender mailSender;


    @PostMapping("/user/getAllTutors")
    public List<Tutor> getTutors() throws ControllerException {
        try {
            //  logger.debug("getting all users");
            return tutorService.getAll();
        } catch (Exception e) {
            //  logger.error("error get all users");
            throw new ControllerException("getUsers", e);
        }
    }

    @PostMapping("/admin/getAllTutors")
    public List<Tutor> getTutorsForAdmin() throws ControllerException {
        try {
            //  logger.debug("getting all users");
            return tutorService.getAll();
        } catch (Exception e) {
            //  logger.error("error get all users");
            throw new ControllerException("getUsers", e);
        }
    }

    @GetMapping("/user/getTutorById/{id}")
    public ResponseEntity<?> getTutorById(@PathVariable(name="id") Long id)throws ControllerException {
        Tutor stuff = null;
        try {
            stuff = tutorService.getById(id);
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @GetMapping("/admin/getTutorById/{id}")
    public ResponseEntity<?> getTutorByIdForAdmin(@PathVariable(name="id") Long id)throws ControllerException {
        Tutor stuff = null;
        try {
            stuff = tutorService.getById(id);
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @PostMapping("/user/createTutor")
    public ResponseEntity<?> createTutor(@RequestBody TutorRequestNoId tutorRequestNoId) throws ControllerException {
        Tutor tutor = new Tutor(
                tutorRequestNoId.getName(),
                tutorRequestNoId.getSurname(),
                tutorRequestNoId.getEmail(),
                tutorRequestNoId.getSubject(),
                tutorRequestNoId.getCost(),
                tutorRequestNoId.getRate());
        try {
            tutorService.create(tutor);
            mailSender.sendMail(tutorRequestNoId.getEmail(), "Объявление", "Ваше объявление успешно опубликовано");
            return new ResponseEntity<>(tutor, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @PutMapping("/user/updateTutorRate")
    public ResponseEntity<?> updateTutorRate(@RequestBody TutorRequestRate  tutorRequestRate )throws ControllerException {
        try {
            Tutor man = tutorService.getById(tutorRequestRate.getId());
            tutorService.updateTutorRateById(
                    tutorRequestRate.getId(),
                    tutorRequestRate.getRate()
            );
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }
    }
    @PutMapping("/admin/updateTutor")
    public ResponseEntity<?> updateTutor(@RequestBody TutorRequest tutorRequest)throws ControllerException {
        try {
            Tutor man = tutorService.getById( tutorRequest.getId());
            tutorService.updateTutorById(
                    tutorRequest.getId(),
                    tutorRequest.getName(),
                    tutorRequest.getSurname(),
                    tutorRequest.getEmail(),
                    tutorRequest.getSubject(),
                    tutorRequest.getCost(),
                    tutorRequest.getRate()
            );
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }
    }

    @PostMapping("/admin/createTutor")
    public ResponseEntity<?> createTutorAdmin(@RequestBody TutorRequestNoId tutorRequestNoId) throws ControllerException {
        Tutor tutor = new Tutor(
                tutorRequestNoId.getName(),
                tutorRequestNoId.getSurname(),
                tutorRequestNoId.getEmail(),
                tutorRequestNoId.getSubject(),
                tutorRequestNoId.getCost(),
                tutorRequestNoId.getRate());
        try {
            tutorService.create(tutor);
            return new ResponseEntity<>(tutor, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @DeleteMapping("/admin/deleteTutorById/{id}")
    public ResponseEntity<?> deleteTutorById(@PathVariable(name="id")Long id)throws ControllerException {
        try {
            tutorService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }
   }

    @GetMapping({"/",""})
    public ModelAndView indexPage(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        model.addAttribute("tutors",tutorService.getAll());
        return modelAndView;
    }


}