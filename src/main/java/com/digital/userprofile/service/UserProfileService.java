package com.digital.userprofile.service;

import com.digital.userprofile.pojo.entity.User;
import com.digital.userprofile.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;


    public User createUser(User user) {
        try {
            System.out.println("Creating user: " + user);
            return userProfileRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


}
