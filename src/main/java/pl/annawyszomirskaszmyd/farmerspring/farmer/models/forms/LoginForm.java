package pl.annawyszomirskaszmyd.farmerspring.farmer.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class LoginForm {
    @Pattern(regexp = "[A-Za-z0-9]{3,30}")
    private String username;

    @Pattern(regexp = "[A-Za-z0-9]{3,30}")
    private String password;
}
