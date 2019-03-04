package pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.forms;

import lombok.Data;
import javax.validation.constraints.Pattern;

@Data
public class RemoveBarnForm {

    @Pattern(regexp = "[A-Za-z0-9]{2,15}")
    private String name;
}
