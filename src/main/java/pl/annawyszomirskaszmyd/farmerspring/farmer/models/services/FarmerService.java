package pl.annawyszomirskaszmyd.farmerspring.farmer.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.forms.*;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.entities.ConfirmationTokenEntity;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.entities.FarmerEntity;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.mappers.RegistrationFormToFarmEntityMapper;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.repositories.FarmerRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class FarmerService {
    private final FarmerRepository farmerRepository;
    private final FarmerHash farmerHash;
    private final FarmerSession farmerSession;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;

    @Autowired
    public FarmerService(FarmerRepository farmerRepository, FarmerHash farmerHash, FarmerSession farmerSession,
                         ConfirmationTokenService confirmationTokenService, EmailSenderService emailSenderService) {
        this.farmerRepository = farmerRepository;
        this.farmerHash = farmerHash;
        this.farmerSession = farmerSession;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSenderService = emailSenderService;
    }

    public enum LoginResponse {
       INCORRECT_CREDENTIALS, ACCOUNT_NOT_ACTIVATED, LOGIN_SUCCESSFUL
    }

    public boolean addFarmer(RegistrationForm registrationForm) {
        if (isUserNameFree(registrationForm.getUsername())) {
            return false;
        }

        registrationForm.setPassword(farmerHash.hashPassword(registrationForm.getPassword()));

        FarmerEntity farmerEntity = new RegistrationFormToFarmEntityMapper().map(registrationForm);

        farmerRepository.save(farmerEntity);

        sendEmailToNewRegisteredFarmer(registrationForm, farmerEntity);

        return true;
    }

    public boolean isUserNameFree(String nickName) {
        return farmerRepository.existsByUsername(nickName);
    }

    public LoginResponse loginResponse(LoginForm loginForm) {
        Optional<FarmerEntity> farmerWithTryToLogin = farmerRepository.getFarmerByUsername(loginForm.getUsername());

        if (farmerWithTryToLogin.isPresent() && farmerHash.isPasswordCorrect(loginForm.getPassword(),
                farmerWithTryToLogin.get().getPassword())) {

            if(!farmerWithTryToLogin.get().isEnabled()) {
                return LoginResponse.ACCOUNT_NOT_ACTIVATED;
            }

            farmerSession.setLogin(true);
            farmerSession.setUserEntity(farmerWithTryToLogin.get());
            return LoginResponse.LOGIN_SUCCESSFUL;

        }

        return LoginResponse.INCORRECT_CREDENTIALS;
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

    public FarmerEntity findById(int farmerId) {
        return farmerRepository.findById(farmerId);
    }

    public void updateFarmer(FarmerEntity farmerEntity){
        farmerRepository.save(farmerEntity);
    }

    private void sendEmailToNewRegisteredFarmer(RegistrationForm registrationForm, FarmerEntity farmerEntity) {
        ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(farmerEntity);
        confirmationTokenService.addConfirmationToken(confirmationTokenEntity);

        emailSenderService.sendEmail(registrationForm.getEmail(), confirmationTokenEntity);
    }

    public void sendEmailWhileTokenIsExpired(FarmerEntity farmerEntity){
        ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(farmerEntity);
        deleteExpiredToken(farmerEntity);
        confirmationTokenService.addConfirmationToken(confirmationTokenEntity);

        emailSenderService.sendEmail(farmerEntity.getEmail(), confirmationTokenEntity);
    }

    private void deleteExpiredToken(FarmerEntity farmerEntity){
        confirmationTokenService.deleteByFarmerId(farmerEntity.getId());
    }
}
