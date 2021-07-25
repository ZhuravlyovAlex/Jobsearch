package ru.innopolis.jobsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.jobsearch.entity.User;


@Controller
    public class DashboardJobSeekerController {

        @RequestMapping("/dashboardJobSeeker")
        public String printHello(Model model) {
            model.addAttribute("messge", "Hello");
            return "dashboardJobSeeker";
        }
    }

