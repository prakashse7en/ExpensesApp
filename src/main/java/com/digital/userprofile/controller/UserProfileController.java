package com.digital.userprofile.controller;

import com.digital.userprofile.mapper.UserProfileMapper;
import com.digital.userprofile.pojo.entity.User;
import com.digital.userprofile.pojo.requestmodel.UserRequestModel;
import com.digital.userprofile.service.UserProfileService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @GetMapping("/api/user")
    @PreAuthorize("hasRole('clientadmin')")
    public String getUserProfile() {
        return "user";
    }

    @PostMapping("/api/user")
    public User createUser(@RequestBody UserRequestModel userRequestModel){
        try{
            UserProfileMapper userProfileMapper =Mappers.getMapper( UserProfileMapper.class );
            User user = userProfileMapper.toEntity(userRequestModel);
            userProfileService.createUser(user);
            //response mapping
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
