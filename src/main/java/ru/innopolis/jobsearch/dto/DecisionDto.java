package ru.innopolis.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.jobsearch.entity.CV;
import ru.innopolis.jobsearch.entity.Vacancy;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DecisionDto {
    private Integer id;
    private Integer vacancyId;
    private Integer cvId;
    private Boolean decision;
}
