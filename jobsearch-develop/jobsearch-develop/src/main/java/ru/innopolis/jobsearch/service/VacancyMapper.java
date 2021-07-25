package ru.innopolis.jobsearch.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.innopolis.jobsearch.dto.VacancyDto;
import ru.innopolis.jobsearch.entity.Vacancy;

@Mapper
public interface VacancyMapper {
    VacancyMapper INSTANCE = Mappers.getMapper(VacancyMapper.class);
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "specialtyName", source = "entity.specialtyName"),
            @Mapping(target = "specification", source = "entity.specification"),
            @Mapping(target = "salary", source = "entity.salary"),
            @Mapping(target = "company", source = "entity.company")
    })
    VacancyDto vacancyToVacancyDTO(Vacancy entity) ;

    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target = "specialtyName", source = "dto.specialtyName"),
            @Mapping(target = "specification", source = "dto.specification"),
            @Mapping(target = "salary", source = "dto.salary"),
            @Mapping(target = "company", source = "dto.company")
    })
    Vacancy vacancyDTOtoVacancy(VacancyDto dto);

}
