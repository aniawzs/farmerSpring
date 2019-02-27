package pl.annawyszomirskaszmyd.farmerspring.models.mappers;

import pl.annawyszomirskaszmyd.farmerspring.models.forms.RegistrationForm;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.FarmerEntity;

public class RegistrationFormToFarmEntityMapper implements Mapper <RegistrationForm,  FarmerEntity> {

    @Override
    public FarmerEntity map(RegistrationForm registrationForm) {
        FarmerEntity farmerEntity = new FarmerEntity();
        farmerEntity.setName(registrationForm.getName());
        farmerEntity.setSurname(registrationForm.getSurname());
        farmerEntity.setEmail(registrationForm.getEmail());
        farmerEntity.setUsername(registrationForm.getUsername());
        farmerEntity.setPassword(registrationForm.getPassword());

      return farmerEntity;
    }
}
