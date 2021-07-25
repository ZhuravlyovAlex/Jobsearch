package ru.innopolis.jobsearch.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.jobsearch.dto.CvDto;
import ru.innopolis.jobsearch.entity.CV;
import ru.innopolis.jobsearch.entity.User;
import ru.innopolis.jobsearch.service.CVService;
import ru.innopolis.jobsearch.service.CvMapper;
import ru.innopolis.jobsearch.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/cvs")
public class CVController {

    private final CVService cvService;
    private final UserService userService;

    @Autowired
    public CVController(CVService cvService, UserService userService) {
        this.cvService = cvService;
        this.userService = userService;
    }

    @GetMapping("/save")
    public String saveCV(Model model) {

        model.addAttribute("cvDto", new CvDto());

        return "cvSave";
    }

    @GetMapping("/update/{id}")
    public String updateCV(@PathVariable("id") int id, Model model) {
        model.addAttribute("cvDto", cvService.getCVById(id));
        return "cvUpdate";
    }

    @GetMapping("/review/{id}")
    public String reviewCV(@PathVariable("id") int id, Model model) {
        model.addAttribute("cvDto", cvService.getCVById(id));
        return "cvReview";
    }

    @PostMapping("/save")
    public String saveCV(@ModelAttribute CvDto cvDto, BindingResult bindingResult, Model model) {
        CV cv = CvMapper.INSTANCE.cvDtoToCvEntity(cvDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        cv.setUser((User) authentication.getPrincipal());
        System.out.println(cv);
        cvService.save(cv);
        return "redirect:/cvs/";
    }

    @PostMapping("/update/{id}")
    public String updateCV(@PathVariable("id") int id, @ModelAttribute CvDto cvDto){
        cvDto.setId(id);
        CV cv = CvMapper.INSTANCE.cvDtoToCvEntity(cvDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        cv.setUser((User) authentication.getPrincipal());
        cvService.update(cv);
        return "redirect:/cvs/";
    }

    @GetMapping("/")
    public String findAllCVs(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        List<CvDto> cvs = CvMapper.INSTANCE.cvEntitiesToCvDtos(cvService.findAllByUserId(currentUser.getId()));
        model.addAttribute("cvs", cvs);

        return "cvList";
    }

    @GetMapping("/delete/{id}")
    public String deleteCV(@PathVariable("id") int id){
        cvService.delete(id);
        return "redirect:/cvs/";
    }
}
