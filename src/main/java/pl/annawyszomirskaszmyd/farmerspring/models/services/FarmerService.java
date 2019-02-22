package pl.annawyszomirskaszmyd.farmerspring.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.annawyszomirskaszmyd.farmerspring.models.forms.*;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.FarmerEntity;
import pl.annawyszomirskaszmyd.farmerspring.models.mappers.RegistrationFormToFarmEntityMapper;
import pl.annawyszomirskaszmyd.farmerspring.models.repositories.FarmerRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class FarmerService {
    final FarmerRepository farmerRepository;
    final FarmerHash farmerHash;
    final FarmerSession farmerSession;

    @Autowired
    public FarmerService(FarmerRepository farmerRepository, FarmerHash farmerHash, FarmerSession farmerSession) {
        this.farmerRepository = farmerRepository;
        this.farmerHash = farmerHash;
        this.farmerSession = farmerSession;
    }

    public boolean addFarmer(RegistrationForm registrationForm) {

        if (isUserNameFree(registrationForm.getUsername())) {
            return false;
        }

        registrationForm.setPassword(farmerHash.hashPassword(registrationForm.getPassword()));
        farmerRepository.save(new RegistrationFormToFarmEntityMapper().map(registrationForm));
        return true;
    }

    public boolean isUserNameFree(String nickName) {
        return farmerRepository.existsByUsername(nickName);
    }

    public boolean isLoginCorrect(LoginForm loginForm) {
        Optional<FarmerEntity> farmerWithTryToLogin = farmerRepository.getFarmerByUsername(loginForm.getUsername());

        if (farmerWithTryToLogin.isPresent() && farmerHash.isPasswordCorrect(loginForm.getPassword(),
                farmerWithTryToLogin.get().getPassword())) {

            farmerSession.setLogin(true);
            farmerSession.setUserEntity(farmerWithTryToLogin.get());

            return true;
        }

        return false;
    }

    public boolean changeFarmerPassword(UpdatePasswordForm changePasswordForm){
        if(farmerHash.isPasswordCorrect(changePasswordForm.getOldPassword(), farmerSession.getUserEntity().getPassword())) {
            farmerRepository.setFarmerPassword(farmerHash.hashPassword(changePasswordForm.getNewPassword()),
                    farmerSession.getUserEntity().getId());

            return true;
        }

        return false;
    }

    public FarmerEntity getFarmerById() {
        return farmerRepository.getFarmerById(getFarmerId());
    }

    public void updateFarmerName(UpdateNameForm updateNameForm) {
        farmerRepository.updateFarmerName(updateNameForm.getName(), farmerSession.getUserEntity().getId());
    }

    public void updateFarmerSurname(UpdateSurnameForm updateSurnameForm) {
        farmerRepository.updateFarmerSurname(updateSurnameForm.getSurname(), farmerSession.getUserEntity().getId());
    }

    public int getFarmerId(){
        return farmerSession.getUserEntity().getId();
    }

    public void logoutFarmer() {
        farmerSession.setLogin(false);
    }
}
