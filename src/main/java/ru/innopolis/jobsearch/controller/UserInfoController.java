package ru.innopolis.jobsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.jobsearch.dto.UserDto;
import ru.innopolis.jobsearch.service.UserInfoService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    private UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("")
    public String getCurrentUserInfo(Model model) {
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userInfoService.getCurrentUserDto());
        System.out.println(userDtoList.get(0));
        model.addAttribute("userInfoList", userDtoList);
        return "userInfo";
    }

    @GetMapping("/save")
    public String saveUserInfo(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "userInfoSave";
    }

    @PostMapping("/save")
    public String saveUserInfo(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, Model model) {

        System.out.println(userDto);
        userInfoService.saveUserInfo(userDto);
        return "redirect:/userInfo";
    }
}