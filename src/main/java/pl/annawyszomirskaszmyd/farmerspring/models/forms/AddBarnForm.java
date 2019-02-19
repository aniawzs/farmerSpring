package pl.annawyszomirskaszmyd.farmerspring.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class AddBarnForm {
    @Pattern(regexp="[A-Za-z0-9]{1,30}")
    private String name;
}
