package ru.innopolis.jobsearch.service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.innopolis.jobsearch.dto.UserDto;
import ru.innopolis.jobsearch.entity.User;
import ru.innopolis.jobsearch.repository.UserRepository;


@AllArgsConstructor
@Service
public class UserInfoService {
    private final UserRepository userRepository;
    public boolean saveUserInfo(UserDto userDto) {
        User user = getCurrentAuthenticationUser();
        user.setPhone(userDto.getPhone());
        //user.setBirthDate(userDto.getBirthDate());
        user.setFio(userDto.getFio());
        user.setEmail(userDto.getEmail());

        userRepository.save(user);
        return true;
    }

    public User getCurrentAuthenticationUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  (User) authentication.getPrincipal();
    }

    public UserDto getCurrentUserDto(){
        Long id = getCurrentAuthenticationUser().getId();
        return UserMapper.INSTANCE.userEntityToUserDto(userRepository.getOne(id));
    }

}
