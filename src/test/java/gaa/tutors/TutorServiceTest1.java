package gaa.tutors;

import gaa.tutors.repository.TutorRepo;
import gaa.tutors.service.TutorService;
import gaa.tutors.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class TutorServiceTest1 {
    @Autowired
    private UserService userService;
    @Autowired
    private TutorRepo tutorRepo;
    @Test
    public void existsUserByLogin() {
        try {
            Assert.assertTrue(userService.existsUserByLogin("hanna"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void existsUserByLoginFalse() {
        try {
            Assert.assertFalse(userService.existsUserByLogin("oooooooooo"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void existsUserByLoginAndPassword() {
        try {
            Assert.assertTrue(userService.existsUserByLoginAndPassword("hanna","test"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void existsById(Long id) throws ServiceException {
        try {
            Assert.assertTrue(tutorRepo.existsById(12L));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
    @Test
    public void testController() throws ServiceException {
        try {
            Assert.assertTrue(tutorRepo.existsById(12L));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Test
    public void testRepo() throws ServiceException {
        try {
            Assert.assertTrue(tutorRepo.existsById(12L));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }


}
