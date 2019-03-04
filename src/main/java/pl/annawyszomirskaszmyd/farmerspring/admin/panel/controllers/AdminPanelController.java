package pl.annawyszomirskaszmyd.farmerspring.admin.panel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.services.AnimalService;
import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.services.BarnService;

@Controller
public class AdminPanelController {
    private final AnimalService animalService;
    private final BarnService barnService;

    @Autowired
    public AdminPanelController(AnimalService animalService, BarnService barnService) {
        this.animalService = animalService;
        this.barnService = barnService;
    }


    @GetMapping("/admin-panel")
    public String adminPanel() {
        return "admin_panel";
    }

    @GetMapping("/admin-panel/five-oldest-animals")
    public String oldestAnimals(Model model){
        if(animalService.isFarmerAnimalListEmpty()){
            model.addAttribute("emptyAnimalList", "Nie masz jeszcze żadnych zwierząt na farmie!");

            return "five_oldest_animals";
        }

        model.addAttribute("oldestAnimals", animalService.returnFiveOldestAnimals());

        return "five_oldest_animals";
    }

    @GetMapping("/admin-panel/five-youngest-animals")
    public String youngestAnimals(Model model){
        if(animalService.isFarmerAnimalListEmpty()){
            model.addAttribute("emptyAnimalList", "Nie masz jeszcze żadnych zwierząt na farmie!");

            return "five_youngest_animals";
        }

        model.addAttribute("youngestAnimals", animalService.returnFiveYoungestAnimals());

        return "five_youngest_animals";
    }

    @GetMapping("/admin-panel/most-numbered-barn")
    public String mostNumberedBarn(Model model){
        if(barnService.isBarnListEmpty()){
            model.addAttribute("emptyBarnList", "Nie masz jeszcze żadnych stodół na farmie!");

            return "most_numbered_barn";
        }

        if(animalService.isFarmerAnimalListEmpty()){
            model.addAttribute("emptyAnimalList", "Nie masz jeszcze żadnych zwierząt na farmie!");

            return "most_numbered_barn";
        }

        model.addAttribute("mostNumberedBarn", barnService.returnMostNumberedBarn());

        return "most_numbered_barn";
    }

    @GetMapping("/admin-panel/most-numbered-animal")
    public String mostNumberedAnimal(Model model){
        if(animalService.isFarmerAnimalListEmpty()){
            model.addAttribute("emptyAnimalList", "Nie masz jeszcze żadnych zwierząt na farmie!");

            return "most_numbered_animal";
        }

        model.addAttribute("mostNumberedAnimal", animalService.returnMostNumberedAnimal());

        return "most_numbered_animal";
    }

    @GetMapping("/admin-panel/all-vaccinated-animals")
    public String vaccinatedAnimals(Model model){
        if(animalService.isFarmerAnimalListEmpty()){
            model.addAttribute("emptyAnimalList", "Nie masz jeszcze żadnych zwierząt na farmie!");

            return "all_vaccinated_animals";
        }

        model.addAttribute("allVaccinatedAnimals", animalService.returnAllVaccinatedAnimals());

        return "all_vaccinated_animals";
    }
}