package pl.annawyszomirskaszmyd.farmerspring.validators;

import org.springframework.stereotype.Component;
import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.repositories.BarnRepository;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.services.FarmerSession;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class AddAnimalValidator implements ConstraintValidator<FarmerBarnsListValidator, Integer> {
    private BarnRepository barnRepository;
    private FarmerSession farmerSession;

    public AddAnimalValidator(BarnRepository barnRepository, FarmerSession farmerSession){
        this.barnRepository = barnRepository;
        this.farmerSession = farmerSession;
    }

    public void initialize(FarmerBarnsListValidator constraint) {}

    @Override
    public boolean isValid(Integer barnId, ConstraintValidatorContext constraintValidatorContext) {
        return barnRepository.existsByIdAndFarmerId(barnId, farmerSession.getUserEntity().getId());
    }
}
