package ru.innopolis.jobsearch.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.innopolis.jobsearch.dto.CompanyDto;
import ru.innopolis.jobsearch.entity.Company;
import ru.innopolis.jobsearch.entity.User;
import ru.innopolis.jobsearch.repository.CompanyRepository;


@AllArgsConstructor
@Service
public class CompanyInfoService {
    private final CompanyRepository companyRepository;
    public boolean saveCompanyInfo(CompanyDto companyDto) {
        Company company = getCurrentAuthenticationUser().getCompany();
        company.setCompanyName(companyDto.getCompanyName());
        company.setInn(companyDto.getInn());
        company.setKpp(companyDto.getKpp());

        companyRepository.save(company);
        return true;
    }

    public User getCurrentAuthenticationUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  (User) authentication.getPrincipal();
    }

    public CompanyDto getCurrentCompanyDto(){
        Integer id = getCurrentAuthenticationUser().getCompany().getId();
        return CompanyMapper.INSTANCE.companyToCompanyDTO(companyRepository.getOne(id));
    }

}


