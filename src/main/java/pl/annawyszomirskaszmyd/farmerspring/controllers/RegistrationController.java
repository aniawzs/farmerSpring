package pl.annawyszomirskaszmyd.farmerspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import  pl.annawyszomirskaszmyd.farmerspring.validators.RegistrationFormValidator;

import pl.annawyszomirskaszmyd.farmerspring.models.forms.RegistrationForm;
import pl.annawyszomirskaszmyd.farmerspring.models.services.FarmerService;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final RegistrationFormValidator registrationFormValidator;
    private final FarmerService farmerService;

    @Autowired
    public RegistrationController(RegistrationFormValidator registrationFormValidator, FarmerService farmerService) {
        this.registrationFormValidator = registrationFormValidator;
        this.farmerService = farmerService;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(registrationFormValidator);
    }


    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute @Valid RegistrationForm registrationForm, BindingResult bindingResult,
                               Model model){
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if(farmerService.addFarmer(registrationForm)){
            return "redirect:/login";
        }

        model.addAttribute("usernameError", "Taki nick został już użyty. Zmień nick aby się zarejestrować!");
        return "registration";
    }
}
