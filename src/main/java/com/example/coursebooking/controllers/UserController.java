package com.example.coursebooking.controllers;

import com.example.coursebooking.model.AppUser;
import com.example.coursebooking.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final AppUserService userService;

    @GetMapping("/profile")
    public String showProfile(Model model){
        model.addAttribute("user", new AppUser());
        return "profile";
    }

    @RequestMapping(path = {"/users","/searchUser"})
    public String getAllUsers(Model model, String keyword){
        List<AppUser> appUserList;
        if(keyword==null){
            appUserList = userService.getAllUserAccounts();
        }else {
            appUserList = userService.getByKeyword(keyword);
        }
        model.addAttribute("appUsers",appUserList);
        return "users";
    }

    @PostMapping("/update")
    public String updateUser(AppUser appUser, Principal principal){
        userService.editUser(appUser,principal);
        return "redirect:/";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/makeEmployee/{id}")
    public String makeEmployee(@PathVariable("id") Long id){
        userService.makeEmployeeFromUserById(id);
        return "redirect:/users";
    }

}
