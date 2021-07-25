package ru.innopolis.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.jobsearch.entity.Company;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {
    private Integer id;
    private String specialtyName;
    private String specification;
    private Double salary;
    private Company company;
}
