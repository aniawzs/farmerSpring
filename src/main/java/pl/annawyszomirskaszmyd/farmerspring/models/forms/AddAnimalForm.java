package pl.annawyszomirskaszmyd.farmerspring.models.forms;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AddAnimalForm{

    @Pattern(regexp = "[A-Za-z]{2,15}")
    private String type;

    @Min(1)
    private int age;

    @Pattern(regexp = "tak|nie")
    private String isVaccinated;

    @NotNull
    @Min(1)
    private int barnId;

}
