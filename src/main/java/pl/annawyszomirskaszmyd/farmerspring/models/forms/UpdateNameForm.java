package pl.annawyszomirskaszmyd.farmerspring.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;


@Data
public class UpdateNameForm {
    @Pattern(regexp = "[A-Za-z]{2,15}")
    private String name;
}
