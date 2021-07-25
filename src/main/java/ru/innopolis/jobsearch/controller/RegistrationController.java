package ru.innopolis.jobsearch.controller;

import ru.innopolis.jobsearch.dto.UserDto;
import ru.innopolis.jobsearch.entity.Company;
import ru.innopolis.jobsearch.entity.User;
import ru.innopolis.jobsearch.service.CompanyService;
import ru.innopolis.jobsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innopolis.jobsearch.service.VacancyService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {


    private UserService userService;
    private CompanyService companyService;
    @Autowired
    public RegistrationController(UserService userService, CompanyService companyService) {

        this.userService = userService;
        this.companyService = companyService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        user.setHasPrivateAccess(false);
        model.addAttribute("userForm", user);
        model.addAttribute("userForm", new User());
        model.addAttribute("companies", companyService.findAllCompanies());
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("binding");
            return "registration";
        }
        /*if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }*/
        if (!userService.saveUser(userDto)){
            System.out.println("save error");
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }
}
