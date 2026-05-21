package ua.com.kerilka.job_exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.kerilka.job_exchange.entity.Roles;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.service.CandidatesService;
import ua.com.kerilka.job_exchange.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class UserAdmin {
    private final UserService userService;
    private final CandidatesService candidateService;

    @GetMapping("/about-us-admin")
    public String aboutUsAdmin() {
        return "about-us-admin";
    }

    @GetMapping("/users-admin")
    public String getUserForAdmin(Model model){
        model.addAttribute("users", userService.findAllUsers());

        return "users-admin";
    }

    @PostMapping("/updateUser")
    public String updateUserForAdmin(Model model,
                                     @RequestParam(name = "id") Long id,
                                     @RequestParam(name = "username") String username){
        Users user = new Users();
        user.setId(id);
        user.setUsername(username);

        userService.updateUser(user);

        return "redirect:/users-admin";
    }
    @PostMapping("/deleteUser")
    public String deleteUserForAdmin(Model model,
                                     @RequestParam(name = "id") Long id){
        userService.deleteUserById(id);

        return "redirect:/users-admin";
    }


    @GetMapping("/roles-admin")
    public String getRolesForAdmin(Model model){
        model.addAttribute("candidates", candidateService.findAllCandidates());


        return "roles-admin";
    }

    @PostMapping("/updateRole")
    public String addNewRoleToUser(@RequestParam(name = "userId") Users user,
                                   @RequestParam(name = "roleId") Long roleId){

        Set<Roles> roles = new HashSet<>();
        user.getRoles().stream().forEach(e->roles.add(e));

        boolean login = false;

        for(Roles r : roles){
            if(r.getId().equals(roleId)) login = true;
        }

        if(!login) userService.addRoleToUser(user.getId(), roleId);


        return "redirect:/roles-admin";
    }
    @PostMapping("/deleteRole")
    public String deleteRoleFromUser(@RequestParam(name = "userId") Users user,
                                     @RequestParam(name = "roleId") Long roleId){

        Set<Roles> roles = new HashSet<>();
        user.getRoles().stream().forEach(e->roles.add(e));

        boolean login = false;

        for(Roles r : roles){
            if(r.getId().equals(roleId)) login = true;
        }

        if(login) userService.deteleRoleFromUser(user.getId(), roleId);

        return "redirect:/roles-admin";
    }
}
