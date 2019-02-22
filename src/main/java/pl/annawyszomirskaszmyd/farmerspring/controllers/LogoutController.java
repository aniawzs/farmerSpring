package pl.annawyszomirskaszmyd.farmerspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.annawyszomirskaszmyd.farmerspring.models.services.FarmerService;


@Controller
public class LogoutController {
    final FarmerService farmerService;

    @Autowired
    public LogoutController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @GetMapping("/admin-panel/farmer-account/logout")
    public String logout(){
        farmerService.logoutFarmer();

        return "redirect:/index";
    }
}
