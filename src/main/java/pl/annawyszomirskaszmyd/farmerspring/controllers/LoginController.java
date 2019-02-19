package pl.annawyszomirskaszmyd.farmerspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.annawyszomirskaszmyd.farmerspring.models.forms.LoginForm;
import pl.annawyszomirskaszmyd.farmerspring.models.services.FarmerService;

import javax.validation.Valid;

@Controller
public class LoginController {
    final FarmerService farmerService;

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

        if(bindingResult.hasErrors()){
            return "login";
        }

        if(farmerService.isLoginCorrect(loginForm)){
            return "redirect:/admin-panel";
        }

        model.addAttribute("incorrectLoginCredentials", "Niepawidłowy username lub hasło. Spróbuj ponownie!");
        return "login";
    }
}
