package gaa.tutors.validator;

import gaa.tutors.models.ContractForm;
import gaa.tutors.models.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ContractValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ContractForm contract =(ContractForm)o;
        if(contract.getId()<0){
            errors.rejectValue("id","negative value");
        }
    }
}