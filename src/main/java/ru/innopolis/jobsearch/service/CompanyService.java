package ru.innopolis.jobsearch.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.jobsearch.dto.CompanyDto;
import ru.innopolis.jobsearch.dto.VacancyDto;
import ru.innopolis.jobsearch.entity.Company;
import ru.innopolis.jobsearch.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CompanyService {
    final CompanyRepository companyRepository;

    public CompanyDto getCompanyById(Integer id){

        return CompanyMapper.INSTANCE.companyToCompanyDTO(companyRepository.getOne(id));
    }

    public CompanyDto save(Company company){

        return CompanyMapper.INSTANCE.companyToCompanyDTO(companyRepository.save(company));
    }
    public List<CompanyDto> findAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(entity->CompanyMapper.INSTANCE.companyToCompanyDTO(entity))
                .collect(Collectors.toList());
    }
}
