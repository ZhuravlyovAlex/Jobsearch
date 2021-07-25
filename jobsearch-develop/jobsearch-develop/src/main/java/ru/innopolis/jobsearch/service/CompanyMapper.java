package ru.innopolis.jobsearch.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.innopolis.jobsearch.dto.CompanyDto;
import ru.innopolis.jobsearch.entity.Company;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "companyName", source = "entity.companyName"),
            @Mapping(target = "inn", source = "entity.inn"),
            @Mapping(target = "kpp", source = "entity.kpp"),
    })
    CompanyDto companyToCompanyDTO(Company entity) ;

    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target = "companyName", source = "dto.companyName"),
            @Mapping(target = "inn", source = "dto.inn"),
            @Mapping(target = "kpp", source = "dto.kpp"),
    })
    Company companyDTOtoCompany(CompanyDto dto);


}
