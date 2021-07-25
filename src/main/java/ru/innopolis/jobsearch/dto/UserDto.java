package ru.innopolis.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.jobsearch.entity.Company;
import ru.innopolis.jobsearch.entity.Role;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String fio;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date birthDate;
    private String passwordConfirm;
    private Company company;
    private Role role;
}
