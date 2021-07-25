package ru.innopolis.jobsearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.innopolis.jobsearch.dto.UserDto;
import ru.innopolis.jobsearch.entity.Company;
import ru.innopolis.jobsearch.entity.User;
import ru.innopolis.jobsearch.repository.CompanyRepository;
import ru.innopolis.jobsearch.repository.RoleRepository;
import ru.innopolis.jobsearch.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(UserDto userDto) {
        User userFromDB = userRepository.findByUsername(userDto.getUsername());

        if (userFromDB != null) {
            return false;
        }

        if(!userDto.getPassword().equals(userDto.getPasswordConfirm())){
            return false;
        }

        userDto.setFio("Ekaterina Volodenko");
        userDto.setEmail("ekaterina@mail.ru");
        userDto.setBirthDate(new Date(2000, 05, 22));
        userDto.setPhone("1234567");
        if(userDto.getCompany() == null){
            userDto.setCompany(companyRepository.findById(1).get());
        }
        userDto.setCompany(companyRepository.getOne(userDto.getCompany().getId()));
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        User userEntity = UserMapper.INSTANCE.userDtoToUserEntity(userDto);
        userRepository.save(userEntity);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usersList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
