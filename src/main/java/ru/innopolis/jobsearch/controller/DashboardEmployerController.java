package ru.innopolis.jobsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.jobsearch.entity.User;

@Controller
//@RequestMapping("/dashboardEmployer")
public class DashboardEmployerController {

        @RequestMapping("/dashboardEmployer")
        public String printHello(Model model) {
            model.addAttribute("message", "Hello");
            return "dashboardEmployer";
        }
    }


