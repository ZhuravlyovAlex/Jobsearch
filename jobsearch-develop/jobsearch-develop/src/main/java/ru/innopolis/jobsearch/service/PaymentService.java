package ru.innopolis.jobsearch.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.innopolis.jobsearch.entity.User;
import ru.innopolis.jobsearch.repository.UserRepository;

@AllArgsConstructor
@Service
public class PaymentService {
    @Autowired
    UserRepository userRepository;

    public boolean setPremiumAccount(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null){
            userFromDB.setHasPrivateAccess(true);
            userRepository.save(userFromDB);
            return true;
        }
        return false;
    }
}
