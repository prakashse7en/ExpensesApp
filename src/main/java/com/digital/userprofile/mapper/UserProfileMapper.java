package com.digital.userprofile.mapper;

import com.digital.userprofile.pojo.entity.User;
import com.digital.userprofile.pojo.requestmodel.UserRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserProfileMapper {

    @Mapping(target = "userId", source = "userId",ignore = true)
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "userEmail", source = "userEmail")
    @Mapping(target = "userPhoneNumber", source = "userPhoneNumber")
    User toEntity(UserRequestModel userRequestModel);



    @Mapping(target = "userId", source = "userId",ignore = true)
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "userEmail", source = "userEmail")
    @Mapping(target = "userPhoneNumber", source = "userPhoneNumber")
    void updateEntity(@MappingTarget User entity, UserRequestModel updateEntity);

    UserRequestModel toRequestModel(User user);
}
