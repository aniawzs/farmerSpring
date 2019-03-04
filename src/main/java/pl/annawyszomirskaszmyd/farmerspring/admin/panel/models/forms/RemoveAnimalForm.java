package pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class RemoveAnimalForm {
    @Pattern(regexp = "[A-Za-z]{2,15}")
    private String type;
}
