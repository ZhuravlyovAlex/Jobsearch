package ru.innopolis.jobsearch.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "login";
    }

    @RequestMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response,
                                  Authentication authResult) throws IOException, ServletException {
        String role = authResult.getAuthorities().toString();

        if (role.contains("ROLE_JOBSEEKER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/dashboardJobSeeker"));
        } else if (role.contains("ROLE_EMPLOYER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/dashboardEmployer"));
        }
    }
}