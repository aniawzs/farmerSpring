package pl.annawyszomirskaszmyd.farmerspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.annawyszomirskaszmyd.farmerspring.models.forms.UpdateNameForm;
import pl.annawyszomirskaszmyd.farmerspring.models.forms.UpdatePasswordForm;
import pl.annawyszomirskaszmyd.farmerspring.models.forms.UpdateSurnameForm;
import pl.annawyszomirskaszmyd.farmerspring.models.services.AnimalService;
import pl.annawyszomirskaszmyd.farmerspring.models.services.BarnService;
import pl.annawyszomirskaszmyd.farmerspring.models.services.FarmerService;

import javax.validation.Valid;

@Controller
public class FarmerProfileController {
    final FarmerService farmerService;
    final BarnService barnService;
    final AnimalService animalService;

    @Autowired
    public FarmerProfileController(FarmerService farmerService, BarnService barnService, AnimalService animalService) {
        this.farmerService = farmerService;
        this.barnService = barnService;
        this.animalService = animalService;
    }

    @GetMapping("/admin-panel/farmer-account")
    public String farmerProfile(){
        return "farmer_profile";
    }

    @GetMapping("/admin-panel/farmer-account/farmer-data")
    public String farmerProfileData(Model model){
        model.addAttribute("farmerData", farmerService.getFarmerById());
        model.addAttribute("farmerId", farmerService.getFarmerId());
        return "farmer_profile_data";
    }

    @GetMapping("/admin-panel/farmer-account/update-password")
    public String changeFarmerPassword(Model model){
        model.addAttribute("changePasswordForm", new UpdatePasswordForm());
        return "farmer_update_password";
    }

    @PostMapping("/admin-panel/farmer-account/update-password")
    public String changePassword(@ModelAttribute @Valid UpdatePasswordForm updatePasswordForm, BindingResult bindingResult,
                                 Model model){

        if(bindingResult.hasErrors()){
            return "farmer_update_password";
        }

        if(!farmerService.changeFarmerPassword(updatePasswordForm)){
            model.addAttribute("incorrectOldPassword", "Stare hasło jest nieprawidłowe");
            return "farmer_update_password";
        }


        model.addAttribute("changePasswordInfo", "Hasło zostało zmienione");
        return "farmer_update_password";
    }

    @GetMapping("/admin-panel/farmer-account/update-name")
    public String changeFarmerName(Model model){
        model.addAttribute("updateNameForm", new UpdateNameForm());

        return "update_name";
    }

    @PostMapping("/admin-panel/farmer-account/update-name")
    public String changeFarmerName(@ModelAttribute @Valid UpdateNameForm updateNameForm, BindingResult bindingResult,
                                   Model model) {

        if(bindingResult.hasErrors()){
            return "update_name";
        }

        farmerService.updateFarmerName(updateNameForm);
        model.addAttribute("nameChangeSuccessInfo", "Imię zostało zmienione");
        return "update_name";
    }
    
    @GetMapping("/admin-panel/farmer-account/update-surname")
    public String changeFarmerSurname(Model model){
        model.addAttribute("updateSurnameForm", new UpdateSurnameForm());
        
        return "update_surname";
    }
    
    @PostMapping("/admin-panel/farmer-account/update-surname")
    public String changeFarmerSurname(@ModelAttribute @Valid UpdateSurnameForm updateSurnameForm, BindingResult bindingResult,
                                      Model model) {
        
        if(bindingResult.hasErrors()){
            return "update_surname";
        }
        
        farmerService.updateFarmerSurname(updateSurnameForm);
        model.addAttribute("surnameChangeSuccessInfo", "Nazwisko zostało poprawnie zmienione");
        
        return "update_surname";
    }

    @GetMapping("/admin-panel/farmer-account/all-barns")
    public String allFarmerBarns(Model model){
        model.addAttribute("allFarmerBarns", barnService.getBarnNamesByFarmerId());
        return "farmer_barns";
    }

    @GetMapping("/admin-panel/farmer-account/all-animals")
    public String allFarmerAnimals(Model model){
        if(animalService.isFarmerAnimalListEmpty()){
            model.addAttribute("emptyAnimalList", "Nie masz jeszcze żadnych zwierząt na farmie");
            return "farmer_all_animals";
        }

        model.addAttribute("allFarmerAnimals", animalService.returnAllFarmerAnimals());
        return "farmer_all_animals";
    }
}
