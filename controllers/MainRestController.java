package gaa.tutors.controllers;

import gaa.tutors.dto.AuthRequest;
import gaa.tutors.dto.AuthResponse;
import gaa.tutors.dto.RegistrationRequest;
import gaa.tutors.dto.UserResponse;
import gaa.tutors.exceptions.ControllerException;
import gaa.tutors.jwt.JwtProvider;
import gaa.tutors.models.User;
import gaa.tutors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class MainRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

   // private static final Logger logger = Logger.getLogger(MainRestController.class);

    @PostMapping("/users")
    public List<User> getUsers() throws ControllerException {
        try {
          //  logger.debug("getting all users");

            return userService.findAll();
        } catch (Exception e) {
          //  logger.error("error get all users");

            throw new ControllerException("getUsers", e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) throws ControllerException
    {
        try{
            //logger.debug("try to login user");

            User user = userService.findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
            if(user != null)
            {
                String token = jwtProvider.generateToken(user.getLogin());
                AuthResponse response = new AuthResponse(token, user.getUserRole().getName());
                System.out.println(user.getUserRole().getName());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else
            {
                throw new ControllerException("not such user");
            }
        } catch (ControllerException e) {
           // logger.error("error login");

            throw new ControllerException("auth", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest)
    {
        //logger.debug("try to register user");
        if(!userService.existsUserByLogin(registrationRequest.getLogin()))
        {
            User user = new User();
            user.setPassword(registrationRequest.getPassword());
            user.setLogin(registrationRequest.getLogin());
            user.setEmail(registrationRequest.getEmail());
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }
    @PostMapping("/authorized")
    public ResponseEntity<?> isAuthorized() throws ControllerException {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/getUser")
    public UserResponse getUser(@RequestHeader(name = "Authorisation") String jwt) throws ControllerException {
        try {

            String userName = jwtProvider.getLoginFromToken(jwt.substring(7));
            User user = userService.findByLogin(userName);

            return new UserResponse(user.getId(), user.getLogin(), user.getUserRole().getName());
        } catch (Exception e) {
            throw new ControllerException("getUser", e);
        }
    }
}