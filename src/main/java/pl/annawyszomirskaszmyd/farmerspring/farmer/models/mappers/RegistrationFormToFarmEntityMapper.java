package pl.annawyszomirskaszmyd.farmerspring.farmer.models.mappers;

import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.mappers.Mapper;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.forms.RegistrationForm;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.entities.FarmerEntity;

public class RegistrationFormToFarmEntityMapper implements Mapper<RegistrationForm,  FarmerEntity> {

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
