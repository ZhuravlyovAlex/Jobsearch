package ru.innopolis.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CvDto {
    private Integer id;
    private String specialtyName;
    private String description;
    private Double salary;
    private Boolean visible;
    private Long userId;
}
