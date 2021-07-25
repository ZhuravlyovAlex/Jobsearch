package ru.innopolis.jobsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.jobsearch.dto.DecisionDto;
import ru.innopolis.jobsearch.dto.VacancyDto;
import ru.innopolis.jobsearch.entity.User;
import ru.innopolis.jobsearch.service.CVService;
import ru.innopolis.jobsearch.service.CompanyService;
import ru.innopolis.jobsearch.service.VacancyMapper;
import ru.innopolis.jobsearch.service.VacancyService;

import javax.validation.Valid;

@Controller
@RequestMapping("/vacancies")
public class VacanciesController {

    private VacancyService vacancyService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CVService cvService;

    @Autowired
    public VacanciesController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping
    public String findAllVacancies(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        String role = currentUser.getRole().getName();
        if (role.equals("ROLE_JOBSEEKER")) {
            model.addAttribute("cvs", cvService.findAllByUserId(currentUser.getId()));
            model.addAttribute("decisionDto", new DecisionDto());
        }
        model.addAttribute("vacancies", vacancyService.findAllVacancies());
        return "vacanciesList";
    }

    @GetMapping(  "/my")
    public String findAllVacanciesByCompanyId(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        String role = currentUser.getRole().getName();
        if (role.equals("ROLE_JOBSEEKER")) {
            model.addAttribute("cvs", cvService.findAllByUserId(currentUser.getId()));
            model.addAttribute("decisionDto", new DecisionDto());
        }
        model.addAttribute("vacancies", vacancyService.findAllByCompanyId(currentUser.getCompany().getId()));
        return "vacanciesList";
    }

    @GetMapping("/save")
    public String saveVacancy(Model model) {
        model.addAttribute("vacancyDto", new VacancyDto());
        return "vacancySave";
    }

    @PostMapping("/save")
    public String saveVacancy(@ModelAttribute("vacancy") @Valid VacancyDto vacancyDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "vacancySave";
        }
        vacancyService.saveVacancy(vacancyDto);
        return "redirect:/vacancies/my";
    }

    @GetMapping("/update/{id}")
    public String updateVacancy(@PathVariable("id") int id, Model model) {
        VacancyDto vacancyDto = VacancyMapper.INSTANCE.vacancyToVacancyDTO(vacancyService.getVacancyById(id));
        model.addAttribute("vacancyDto", vacancyDto);
        return "vacancyUpdate";
    }

    @GetMapping("/review/{id}")
    public String reviewVacancy(@PathVariable("id") int id, Model model) {
        VacancyDto vacancyDto = VacancyMapper.INSTANCE.vacancyToVacancyDTO(vacancyService.getVacancyById(id));
        model.addAttribute("vacancyDto", vacancyDto);
        return "vacancyReview";
    }

    @PostMapping("/update/{id}")
    public String updateVacancy(@PathVariable("id") int id, @ModelAttribute VacancyDto vacancyDto) {
        vacancyService.update(vacancyDto);
        return "redirect:/vacancies/my";
    }

    @GetMapping("/delete/{id}")
    public String deleteVacancy(@PathVariable("id") int id) {
        vacancyService.delete(id);
        return "redirect:/vacancies/my";
    }
}
