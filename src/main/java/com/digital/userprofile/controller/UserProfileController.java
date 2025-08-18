package com.digital.userprofile.controller;

import com.digital.userprofile.exception.UserNotFoundException;
import com.digital.userprofile.mapper.UserProfileMapper;
import com.digital.userprofile.pojo.entity.User;
import com.digital.userprofile.pojo.requestmodel.UserRequestModel;
import com.digital.userprofile.service.UserProfileService;
import io.micrometer.common.util.StringUtils;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserProfileController {

    private final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    UserProfileService userProfileService;

    @GetMapping("/api/user/{userId}")
    @PreAuthorize("hasRole('clientadmin')")
    public User getUserProfile(@PathVariable("userId") UUID userId) throws UserNotFoundException {
        return userProfileService.getUserById(userId);
    }

    @PostMapping("/api/user")
    @PreAuthorize("hasRole('clientadmin')")
    public User createUser(@RequestBody UserRequestModel userRequestModel){
        User user = null;
        try{
            UserProfileMapper userProfileMapper =Mappers.getMapper( UserProfileMapper.class );
            user = userProfileMapper.toEntity(userRequestModel);
            user =userProfileService.createUser(user);
        }catch(Exception e){
            logger.error("error occurred while creating user", e);
        }
        return user;
    }


    @PatchMapping("/api/user/{userId}")
    @PreAuthorize("hasRole('clientadmin')")
    public UserRequestModel patchUser(@PathVariable("userId") UUID userId,@RequestBody UserRequestModel userRequestModel) throws UserNotFoundException {
        User user = null;

        if(StringUtils.isEmpty(String.valueOf(userId))){
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        user = userProfileService.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found with ID: " + userRequestModel.getUserId());
        }
        logger.info("Patching user with ID: {}", userId);
        UserProfileMapper userProfileMapper =Mappers.getMapper( UserProfileMapper.class );
        userProfileMapper.updateEntity(user,userRequestModel);
        user = userProfileService.updateUser(user);
        userRequestModel =  userProfileMapper.toRequestModel(user);
        return userRequestModel;
    }

}
