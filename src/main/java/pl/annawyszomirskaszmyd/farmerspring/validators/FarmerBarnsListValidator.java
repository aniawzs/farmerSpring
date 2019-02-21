package pl.annawyszomirskaszmyd.farmerspring.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddAnimalValidator.class)
public @interface FarmerBarnsListValidator {
    String message() default "Nie posiadasz takiego numeru stodoły na farmie";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
