package com.digital.userprofile.service;

import com.digital.userprofile.exception.UserNotFoundException;
import com.digital.userprofile.pojo.entity.User;
import com.digital.userprofile.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

    //update user
    public User updateUser(User user) {
        try {
            //fetch and update user
            User existingUser = userProfileRepository.findByUserId(user.getUserId());

            if(existingUser == null) {
                throw new UserNotFoundException("User not found with ID: " + user.getUserId());
            }
            System.out.println("Updating user: " + existingUser.getUserId());
            return userProfileRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
