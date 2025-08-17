package com.digital.userprofile.event.producer;

import com.digital.userprofile.pojo.entity.User;
import com.digital.userprofile.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserProfileEventProducer {

    private final Logger logger = LoggerFactory.getLogger(UserProfileEventProducer.class);


    @Autowired
    KafkaProducerService kafkaProducerService;

    @Async
    public void sendUserProfileEvent(User user) {
        try{
            if(null != user){
                String userString = user.getUserId().toString();
                kafkaProducerService.sendMessage(userString.getBytes());
                logger.info("User profile event sent for user: {}", user.getUserId());
            }
        }catch (Exception e){
            logger.error("Error occurred while sending user profile event", e);
        }


    }
}
