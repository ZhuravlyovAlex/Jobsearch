package ru.innopolis.jobsearch.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.innopolis.jobsearch.dto.VacancyDto;
import ru.innopolis.jobsearch.entity.CV;
import ru.innopolis.jobsearch.entity.User;
import ru.innopolis.jobsearch.entity.Vacancy;
import ru.innopolis.jobsearch.repository.VacancyRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service

public class VacancyService {
    private final VacancyRepository vacancyRepository;

    public boolean saveVacancy(VacancyDto vacancyDto) {
        vacancyDto.setCompany(getCurrentAuthenticationUser().getCompany());
        vacancyRepository.save(VacancyMapper.INSTANCE.vacancyDTOtoVacancy(vacancyDto));
        return true;
    }

    public List<VacancyDto> findAllVacancies() {
        return vacancyRepository.findAll()
                .stream()
                .map(VacancyMapper.INSTANCE::vacancyToVacancyDTO)
                .collect(Collectors.toList());
    }

    public Vacancy getVacancyById(Integer id) {
        return vacancyRepository.getOne(id);
    }

    public VacancyDto update(VacancyDto vacancyDto) {
        vacancyDto.setCompany(getCurrentAuthenticationUser().getCompany());
        Vacancy vacancy = vacancyRepository.save(VacancyMapper.INSTANCE.vacancyDTOtoVacancy(vacancyDto));
        return VacancyMapper.INSTANCE.vacancyToVacancyDTO(vacancy);
    }

    public void delete(int id) {
        vacancyRepository.deleteById(id);
    }

    public List<Vacancy> findAllByCompanyId(Integer companyId) {
        return vacancyRepository.findAllByCompanyId(companyId);
    }

    private User getCurrentAuthenticationUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

}