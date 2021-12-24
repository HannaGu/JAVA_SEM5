package gaa.tutors.controllers;

import gaa.tutors.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TutorController {
    @Autowired
    private TutorService tutorService;

    /*@GetMapping("/getAllTutorsForUser")
    public ResponseEntity<?> getAllTutors()throws ControllerException {
        try {
            return new ResponseEntity<>(tutorService.getAll(), HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }*/
    @GetMapping("/getAllTutorsForUser")
    public ModelAndView getAllTutors(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("tutors", tutorService.getAll());
        return modelAndView;
    }


    @GetMapping("/user/getSearchPage")
    public ModelAndView getSearchPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        return modelAndView;
    }
}
