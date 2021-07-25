package ru.innopolis.jobsearch.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.innopolis.jobsearch.dto.CvDto;
import ru.innopolis.jobsearch.dto.DecisionDto;
import ru.innopolis.jobsearch.entity.CV;
import ru.innopolis.jobsearch.entity.Decision;

import java.util.List;

@Mapper
public interface DecisionMapper {
    DecisionMapper INSTANCE = Mappers.getMapper(DecisionMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "vacancyId", source = "entity.vacancy.id"),
            @Mapping(target = "cvId", source = "entity.cv.id"),
            @Mapping(target = "decision", source = "entity.decision"),
    })
    DecisionDto decisionEntityToDecisionDto(Decision entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "decision", source = "dto.decision")
    })
    Decision decisionDtoToDecisionEntity(DecisionDto dto);

    List<DecisionDto> decisionEntitiesToDecisionDtos(List<Decision> cvs);

}
