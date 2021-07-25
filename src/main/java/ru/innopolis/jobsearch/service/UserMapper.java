package ru.innopolis.jobsearch.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.innopolis.jobsearch.dto.UserDto;
import ru.innopolis.jobsearch.entity.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "username", source = "entity.username"),
            @Mapping(target = "password", source = "entity.password"),
            @Mapping(target = "passwordConfirm", source = "entity.passwordConfirm"),
            @Mapping(target = "role", source = "entity.role"),
            @Mapping(target = "fio", source = "entity.fio"),
            @Mapping(target = "phone", source = "entity.phone"),
            @Mapping(target = "email", source = "entity.email"),
            @Mapping(target = "birthDate", source = "entity.birthDate"),
            @Mapping(target = "company", source = "entity.company"),
//            @Mapping(target = "hasPrivateAccess", source = "entity.hasPrivateAccess")


    })
    UserDto userEntityToUserDto(User entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "username", source = "dto.username"),
            @Mapping(target = "password", source = "dto.password"),
            @Mapping(target = "passwordConfirm", source = "dto.passwordConfirm"),
            @Mapping(target = "role", source = "dto.role"),
            @Mapping(target = "fio", source = "dto.fio"),
            @Mapping(target = "phone", source = "dto.phone"),
            @Mapping(target = "email", source = "dto.email"),
            @Mapping(target = "birthDate", source = "dto.birthDate"),
            @Mapping(target = "company", source = "dto.company"),
//            @Mapping(target = "hasPrivateAccess", source = "dto.hasPrivateAccess")

    })
    User userDtoToUserEntity(UserDto dto);
}
