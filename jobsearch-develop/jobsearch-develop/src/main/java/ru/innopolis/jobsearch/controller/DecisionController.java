package ru.innopolis.jobsearch.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.jobsearch.dto.DecisionDto;
import ru.innopolis.jobsearch.entity.*;
import ru.innopolis.jobsearch.service.CVService;
import ru.innopolis.jobsearch.service.DecisionMapper;
import ru.innopolis.jobsearch.service.DecisionService;
import ru.innopolis.jobsearch.service.VacancyService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/decisions")
@AllArgsConstructor
public class DecisionController {
    private final DecisionService decisionService;
    private final CVService cvService;
    private final VacancyService vacancyService;

    @PostMapping("/save")
    public String saveDecision(@ModelAttribute("decisionDto") DecisionDto decisionDto, BindingResult result, Model model) {
        DecisionMapper mapper = DecisionMapper.INSTANCE;
        Decision decision = mapper.decisionDtoToDecisionEntity(decisionDto);
        Decision trySelectDecision = decisionService.getDecisionByVacancyAndCv(decisionDto.getVacancyId(), decisionDto.getCvId());
        if(trySelectDecision!=null){
            return "redirect:/vacancies";
        }
        decision.setCv(cvService.getCVById(decisionDto.getCvId()));
        decision.setVacancy(vacancyService.getVacancyById(decisionDto.getVacancyId()));
        decision.setDecision(false);
        decisionService.saveDecision(decision);
        return "redirect:/vacancies";
    }

    @PostMapping("/accept")
    public String acceptApplication(@ModelAttribute("decisionDto") DecisionDto decisionDto){
        DecisionMapper mapper = DecisionMapper.INSTANCE;
        if(decisionDto.getDecision() == null){
            decisionDto.setDecision(false);
        }
        Decision decision = mapper.decisionDtoToDecisionEntity(decisionDto);
        Decision dbDecision = decisionService.getById(decision);
        dbDecision.setDecision(decision.getDecision());

        decisionService.saveDecision(dbDecision);
        return "redirect:/decisions";
    }

    @GetMapping
    public String findAllDecisions(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        String currentRole = currentUser.getRole().getName();
        if(currentRole.equals("ROLE_JOBSEEKER")) {
            List<Integer> cvs = cvService.findAllByUserId(currentUser.getId())
                    .stream()
                    .mapToInt(CV::getId)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("decisions", decisionService.getDecisionsByCvs(cvs));
        }else{
            List<Integer> vacancies = vacancyService.findAllByCompanyId(currentUser.getCompany().getId())
                    .stream()
                    .mapToInt(Vacancy::getId)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("decisions", decisionService.getDecisionsByVacancies(vacancies));
            model.addAttribute("decisionDto", new DecisionDto());
        }
        return "decisionList";
    }

}
