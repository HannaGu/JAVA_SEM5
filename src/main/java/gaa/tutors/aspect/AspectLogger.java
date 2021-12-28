package gaa.tutors.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class AspectLogger {
    private Logger log = Logger.getLogger(getClass().toString());

    @Pointcut("execution(* gaa.tutors.controllers.MainRestController.*(..))")
    public void calledAtMainRestController(){}
    @After("calledAtMainRestController()")
    public void log(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }
}
