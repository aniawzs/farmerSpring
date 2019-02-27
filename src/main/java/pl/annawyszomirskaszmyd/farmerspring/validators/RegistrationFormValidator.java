package pl.annawyszomirskaszmyd.farmerspring.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.annawyszomirskaszmyd.farmerspring.models.forms.RegistrationForm;

@Component
public class RegistrationFormValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
       return RegistrationForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                "required.name", "Podaj imię");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"surname",
                "required.surname", "Podaj nazwisko");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "required.email", "Podaj nick");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
                "required.username", "Podaj nick");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "required.password", "Podaj hasło");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "required.confirmPassword", "Potwierdź hasło");

        RegistrationForm registrationForm = (RegistrationForm)target;


        if(!(registrationForm.getPassword().equals(registrationForm.getConfirmPassword()))){
            errors.rejectValue("confirmPassword", "required.notTheSamePasswords", "Podane hasła są różne!");
        }
    }

}
