package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kerilka.job_exchange.entity.Profiles;
import ua.com.kerilka.job_exchange.service.ProfilesService;

@Controller
@RequiredArgsConstructor
public class ProfileManager {

    private final ProfilesService profilesService;



    @GetMapping("/profile-manager")
    public String getProfilesPageForManager(Model model){

        model.addAttribute("profiles", profilesService.findAllProfiles());

        return "profile-manager";
    }

    @PostMapping("/updateProfile")
    public String updateProfileFromManager(Model model,
                                           @RequestParam(name = "id") Long id,
                                           @RequestParam(name = "firstName") String firstName,
                                           @RequestParam(name = "lastName") String lastName,
                                           @RequestParam(name = "middleName") String middleName,
                                           @RequestParam(name = "address") String address,
                                           @RequestParam(name = "phone") String phone,
                                           @RequestParam(name = "email") String email,
                                           @RequestParam(name = "age") Integer age
                                           ){

        Profiles profile = new Profiles();
        profile.setId(id);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setMiddleName(middleName);
        profile.setAddress(address);
        profile.setPhone(phone);
        profile.setEmail(email);
        profile.setAge(age);

        profilesService.updateProfile(profile);

        return "redirect:/profile-manager";
    }

    @PostMapping("/deleteProfile")
    public String deleteProfileFromManager(Model model,
                                           @RequestParam(name = "id") Long id){
        profilesService.deleteProfileById(id);

        return "redirect:/profile-manager";
    }
}
