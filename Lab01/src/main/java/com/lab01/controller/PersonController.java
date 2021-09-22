package com.lab01.controller;

import com.lab01.forms.PersonForm;
import com.lab01.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    private static List<Person> people = new ArrayList<Person>();
    static {
        people.add(new Person("Jim Jarmusch", 68));
        people.add(new Person("Cortney Love", 57));
    }

    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        return modelAndView;
    }

    @RequestMapping(value = {"/allpeople"}, method = RequestMethod.GET)
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("peoplelist");
        model.addAttribute("people", people);
        return modelAndView;
    }

    @RequestMapping(value = {"/addperson"}, method = RequestMethod.GET)
    public ModelAndView showAddPersonPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addperson");
        PersonForm personForm = new PersonForm();
        model.addAttribute("personform", personForm);
        return modelAndView;
    }

    @RequestMapping(value = {"/addperson"}, method = RequestMethod.POST)
    public ModelAndView savePerson(Model model, //
                                   @ModelAttribute("personform") PersonForm personForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("peoplelist");
        String name = personForm.getName();
        int age = personForm.getAge();
        if (name != null && name.length() > 0 //
                && age >0) {
            Person newPerson = new Person(name, age);
            people.add(newPerson);
            model.addAttribute("people", people);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("addperson");
        return modelAndView;
    }

}
