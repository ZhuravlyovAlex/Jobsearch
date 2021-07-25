package ru.innopolis.jobsearch.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.innopolis.jobsearch.dto.CvDto;
import ru.innopolis.jobsearch.entity.CV;

import java.util.List;

@Mapper
public interface CvMapper {
    CvMapper INSTANCE = Mappers.getMapper(CvMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "specialtyName", source = "entity.specialtyName"),
            @Mapping(target = "description", source = "entity.description"),
            @Mapping(target = "salary", source = "entity.salary"),
            @Mapping(target = "visible", source = "entity.visible"),
            @Mapping(target = "userId", source = "entity.user.id"),
    })
    CvDto cvEntityToCvDto(CV entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "specialtyName", source = "dto.specialtyName"),
            @Mapping(target = "description", source = "dto.description"),
            @Mapping(target = "salary", source = "dto.salary"),
            @Mapping(target = "visible", source = "dto.visible")
    })
    CV cvDtoToCvEntity(CvDto dto);
    List<CvDto> cvEntitiesToCvDtos(List<CV> cvs);
}
