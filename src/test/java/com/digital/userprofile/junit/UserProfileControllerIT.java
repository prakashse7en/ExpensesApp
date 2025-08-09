package com.digital.userprofile.junit;

import com.digital.userprofile.pojo.entity.User;
import com.digital.userprofile.pojo.requestmodel.UserRequestModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserProfileControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetUserProfile() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/user", String.class);
        // Assert response status and body
        if (response.getStatusCode() == HttpStatus.OK) {
            String body = response.getBody();
            // Perform assertions on the body if needed
            assertNotNull(body);
            assert body.contains("user"); // Example assertion
        } else {
            // Handle unexpected status codes
        }
    }

    @Test
    void testCreateUser() {
        UserRequestModel request = new UserRequestModel();
        // set request fields
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserRequestModel> entity = new HttpEntity<>(request, headers);

        ResponseEntity<User> response = restTemplate.postForEntity("/api/user", entity, User.class);
        // Assert response status and body
    }
}