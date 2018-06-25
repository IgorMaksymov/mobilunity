package org.maksymov.demo.mapper;

import org.maksymov.demo.controller.payload.UserWithCredentials;
import org.maksymov.demo.db.entity.AccountEntity;
import org.maksymov.demo.db.entity.UserProfileEntity;
import org.maksymov.demo.dto.UserProfileWithLoginDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "login", source = "account.userName")
    @Mapping(target = "hashedPassword", source = "hashedPassword")
    AccountEntity toAccountEntity(UserWithCredentials account, byte[] hashedPassword);

    @Mapping(target = "id", source = "accountEntity.id")
    @Mapping(target = "userName", source = "accountEntity.login")
    UserProfileWithLoginDTO toDto(AccountEntity accountEntity, UserProfileEntity entity);

    UserProfileEntity toProfileEntity(Long id, UserWithCredentials entity);

}