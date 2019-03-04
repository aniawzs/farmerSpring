package pl.annawyszomirskaszmyd.farmerspring.farmer.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class RegistrationForm {
    @Pattern(regexp = "[A-Za-z]{3,30}")
    private String name;

    @Pattern(regexp = "[A-Za-z]{3,30}")
    private String surname;


    @Pattern(regexp="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,6}$")
    private String email;

    @Pattern(regexp = "[A-Za-z0-9]{3,30}")
    private String username;

    @Pattern(regexp = "[A-Za-z0-9]{2,30}")
    private String password;

    @Pattern(regexp = "[A-Za-z0-9]{2,30}")
    private String confirmPassword;
}
