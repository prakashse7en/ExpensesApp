package com.digital.userprofile.mapper;

import com.digital.userprofile.pojo.entity.User;
import com.digital.userprofile.pojo.requestmodel.UserRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserProfileMapper {

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "userEmail", source = "userEmail")
    User toEntity(UserRequestModel userRequestModel);
}
