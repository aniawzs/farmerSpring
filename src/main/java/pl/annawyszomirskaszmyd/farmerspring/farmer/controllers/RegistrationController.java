package pl.annawyszomirskaszmyd.farmerspring.farmer.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.entities.ConfirmationTokenEntity;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.entities.FarmerEntity;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.services.ConfirmationTokenService;
import pl.annawyszomirskaszmyd.farmerspring.validators.RegistrationFormValidator;

import pl.annawyszomirskaszmyd.farmerspring.farmer.models.forms.RegistrationForm;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.services.FarmerService;

import javax.validation.Valid;
import java.util.Calendar;

@Controller
public class RegistrationController {
    private final RegistrationFormValidator registrationFormValidator;
    private final FarmerService farmerService;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public RegistrationController(RegistrationFormValidator registrationFormValidator, FarmerService farmerService,
                                  ConfirmationTokenService confirmationTokenService) {
        this.registrationFormValidator = registrationFormValidator;
        this.farmerService = farmerService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(registrationFormValidator);
    }


    @GetMapping("/pl/annawyszomirskaszmyd/farmerspring/farmer")
    public String registration(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping("/pl/annawyszomirskaszmyd/farmerspring/farmer")
    public String registration(@ModelAttribute @Valid RegistrationForm registrationForm, BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (farmerService.addFarmer(registrationForm)) {
            return "redirect:/login";
        }

        model.addAttribute("usernameError", "Taki nick został już użyty. Zmień nick aby się zarejestrować!");
        return "registration";
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmFarmerAccount(Model model, @RequestParam("token") String confirmationToken) {
        ConfirmationTokenEntity confirmationTokenEntity = confirmationTokenService.findByConfirmationToken(confirmationToken);

        if (confirmationTokenEntity.getConfirmationToken() == null) {
            model.addAttribute("invalidToken", "Token jest niepoprawny.");
            return "confirm_account";
        }

        FarmerEntity farmerEntity = farmerService.findById(confirmationTokenEntity.getFarmerEntity().getId());

        Calendar cal = Calendar.getInstance();
        if ((confirmationTokenEntity.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("expiredToken", "Token już wygasł. Na Twój adres email został wysłany nowy token. \n" +
                    "Kliknij w niego aby dokończyć proces rejestracji.");
            farmerService.sendEmailWhileTokenIsExpired(farmerEntity);
            return "confirm_account";
        }



        farmerEntity.setEnabled(true);
        farmerService.updateFarmer(farmerEntity);

        model.addAttribute("confirmAccountMessage", "Konto zostało potwierdzone");

        return "confirm_account";
    }
}
