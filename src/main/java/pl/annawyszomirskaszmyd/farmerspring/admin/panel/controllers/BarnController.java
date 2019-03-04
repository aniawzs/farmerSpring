package pl.annawyszomirskaszmyd.farmerspring.admin.panel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.forms.AddBarnForm;
import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.forms.RemoveBarnForm;
import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.services.BarnService;

import javax.validation.Valid;

@Controller
public class BarnController {
    private final BarnService barnService;

    @Autowired
    public BarnController(BarnService barnService) {
        this.barnService = barnService;
    }

    @GetMapping("/admin-panel/add-barn")
    public String addBarn(Model model){
        model.addAttribute("addBarnForm", new AddBarnForm());

        return "add_barn";
    }


    @PostMapping("/admin-panel/add-barn")
    public String addBarn(@ModelAttribute @Valid AddBarnForm addBarnForm, BindingResult bindingResult, Model model){
        if(barnService.existsByNameAndFarmerId(addBarnForm.getName())){
            model.addAttribute("existByName", "Farma o podanej nazwie już istnieje na farmie!");
            return "add_barn";
        }

        if(bindingResult.hasErrors()){
            return "add_barn";
        }

        model.addAttribute("barnAddedSuccessfully", "Stodoła została dodana!");
        barnService.addBarn(addBarnForm);

        return "add_barn";
    }



    @GetMapping("/admin-panel/remove-barn")
    public String removeBarn(Model model){
        if(barnService.isBarnListEmpty()){
            model.addAttribute("barnListEmpty", "Nie masz jeszczez żadnych stodół na farmie");

            return "remove_barn";
        }

        model.addAttribute("removeBarnForm", new RemoveBarnForm());
        return "remove_barn";
    }

    @PostMapping("/admin-panel/remove-barn")
    public String removeBarn(@ModelAttribute @Valid RemoveBarnForm removeBarnForm, BindingResult bindingResult,
                             Model model){
        if(bindingResult.hasErrors()){
            return "remove_barn";
        }

        if(!barnService.existsByNameAndFarmerId(removeBarnForm.getName())){
            model.addAttribute("barnDoesNotExist", "Podana nazwa stodoły nie istnieje na farmie");
            return "remove_barn";
        }

        barnService.removeBarn(removeBarnForm.getName());
        model.addAttribute("removeBarnSuccess", "Stodoła została usunięta");

        return "remove_barn";
    }
}
