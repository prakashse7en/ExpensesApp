package com.digital.userprofile.service;

import com.digital.userprofile.controller.UserProfileController;
import com.digital.userprofile.event.producer.UserProfileEventProducer;
import com.digital.userprofile.exception.UserNotFoundException;
import com.digital.userprofile.pojo.entity.User;
import com.digital.userprofile.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService {

    private Logger logger = LoggerFactory.getLogger(UserProfileService.class);

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileEventProducer userProfileEventProducer;


    public User createUser(User user) {
        try {

            return userProfileRepository.save(user);
        } catch (Exception e) {
            logger.error("error occurred",e);
        }
        return null;
    }

    public User getUserById(UUID userId) throws UserNotFoundException {
        try {
            User user = userProfileRepository.findByUserId(userId);
            if (user == null) {
                throw new UserNotFoundException("User not found with ID: " + userId);
            }
            return user;
        } catch (Exception e) {
            logger.error("error occurred",e);
            throw e;
        }
    }

    public User updateUser(User user) {
        try {
            User existingUser = userProfileRepository.findByUserId(user.getUserId());

            if(existingUser == null) {
                throw new UserNotFoundException("User not found with ID: " + user.getUserId());
            }
            logger.debug("Updating user: {}" ,existingUser.getUserId());
            user =userProfileRepository.save(user);

            userProfileEventProducer.sendUserProfileEvent(user);
            return user;
        } catch (Exception e) {
            logger.error("error occurred",e);
        }
        return null;
    }

}
