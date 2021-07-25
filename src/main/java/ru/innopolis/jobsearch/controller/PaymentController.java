package ru.innopolis.jobsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.innopolis.jobsearch.dto.CardDto;
import ru.innopolis.jobsearch.entity.User;
import ru.innopolis.jobsearch.service.PaymentService;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/payment")
    public String doPay(Model model) {
        model.addAttribute("card", new CardDto());
        return "payment";
    }

    @PostMapping(value = "/payment")
    public String enterCardInfo(Model model, @ModelAttribute("card") CardDto card) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange("http://localhost:8092/payment/pay",
                    HttpMethod.POST, new HttpEntity<>(card), String.class);
            paymentService.setPremiumAccount();
        } catch (HttpClientErrorException e) {
            model.addAttribute("result", "Произошла ошибка при проведении платеж, повторите попытку позже");
            return "result";
        }
        model.addAttribute("result", responseEntity.getBody().toString());
//        User user = (User) authentication.getPrincipal();
//        user.setHasPrivateAccess(true);
        return "result";
    }
}
