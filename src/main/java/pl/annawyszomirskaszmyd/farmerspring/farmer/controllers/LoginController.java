package pl.annawyszomirskaszmyd.farmerspring.farmer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.forms.LoginForm;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.services.FarmerService;

import javax.validation.Valid;

@Controller
public class LoginController {
    private final FarmerService farmerService;

    @Autowired
    public LoginController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginForm", new LoginForm());

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Valid LoginForm loginForm, BindingResult bindingResult, Model model){
        FarmerService.LoginResponse loginResponse = farmerService.loginResponse(loginForm);

        if(bindingResult.hasErrors()){
            return "login";
        }

        if(loginResponse == FarmerService.LoginResponse.LOGIN_SUCCESSFUL){
            return "redirect:/admin-panel";
        }

        model.addAttribute("incorrectLogin", loginResponse);
        return "login";
    }
}
