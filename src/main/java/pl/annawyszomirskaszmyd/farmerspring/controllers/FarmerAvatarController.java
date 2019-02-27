package pl.annawyszomirskaszmyd.farmerspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.annawyszomirskaszmyd.farmerspring.models.services.FarmerAvatarService;

@Controller
public class FarmerAvatarController {
    private final FarmerAvatarService farmerAvatarService;

    @Autowired
    public FarmerAvatarController(FarmerAvatarService farmerAvatarService) {
        this.farmerAvatarService = farmerAvatarService;
    }

    @PostMapping("/admin-panel/farmer-account/avatar/{id}")
    public String uploadAvatar(@RequestParam("avatar")MultipartFile multipartFile, @PathVariable("id") int id,
                               RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("avatarInfo", farmerAvatarService.uploadAvatar(multipartFile, id));

        return "redirect:/admin-panel/farmer-account/farmer-data";
    }
}
