package ru.innopolis.jobsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.jobsearch.dto.CompanyDto;
import ru.innopolis.jobsearch.dto.UserDto;
import ru.innopolis.jobsearch.service.CompanyInfoService;
import ru.innopolis.jobsearch.service.UserInfoService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/companyInfo")

public class CompanyInfoController {
    private CompanyInfoService companyInfoService;

    @Autowired
    public CompanyInfoController(CompanyInfoService companyInfoService) {
        this.companyInfoService = companyInfoService;
    }

        @GetMapping("")
        public String getCurrentCompanyInfo(Model model) {
            List<CompanyDto> companyDtoList = new ArrayList<>();
            companyDtoList.add(companyInfoService.getCurrentCompanyDto());
            System.out.println(companyDtoList.get(0));
            model.addAttribute("companyInfoList", companyDtoList);
            return "companyInfo";
        }

        @GetMapping("/save")
        public String saveCompanyInfo(Model model) {
            model.addAttribute("companyDto", new CompanyDto());
            return "companyInfoSave";
        }

        @PostMapping("/save")
        public String saveCompanyInfo(@ModelAttribute("company") @Valid CompanyDto companyDto, BindingResult bindingResult, Model model) {

            System.out.println(companyDto);
            companyInfoService.saveCompanyInfo(companyDto);
            return "redirect:/companyInfo";
        }

}
