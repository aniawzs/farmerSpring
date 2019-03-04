package pl.annawyszomirskaszmyd.farmerspring.farmer.models.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdatePasswordForm {
    @NotNull
    @Size(min=2, max=30)
    private String oldPassword;

    @NotNull
    @Size(min=2, max=30)
    private String newPassword;
}
