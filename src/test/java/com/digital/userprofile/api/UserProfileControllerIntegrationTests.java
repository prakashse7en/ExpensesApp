package com.digital.userprofile.api;

import com.digital.userprofile.ContainersConfig;
import com.digital.userprofile.pojo.entity.User;
import com.digital.userprofile.repository.UserProfileRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.MySQLContainer;

import static com.digital.userprofile.api.utils.TestUtils.getPostResponse;
import static io.restassured.RestAssured.given;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(ContainersConfig.class)
class UserProfileControllerIntegrationTests {

  static final String GRANT_TYPE_CLIENT_CREDENTIALS = "password";
  static final String CLIENT_ID = "expensesclientadmin"; //TODO JWT change
  static final String PASSWORD = "password";
  static final String USERNAME = "expensesuser";

  @LocalServerPort
  private int port;

  @Autowired
  OAuth2ResourceServerProperties oAuth2ResourceServerProperties;

  @Autowired
  UserProfileRepository userProfileRepository;

  @BeforeEach
  void setup() {
    RestAssured.port = port;
  }

  static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.30")
          .withDatabaseName("testcontainer")
          .withUsername("test")
          .withPassword("test");

  @BeforeAll
  static void beforeAll() {
    mySQLContainer.start();
  }


  @Test
  void shouldGetUnauthorizedWhenCreateGetUserWithoutAuthToken() {
    given()
            .contentType("application/json")
            .when()
            .get("/api/user")
            .then()
            .statusCode(401);
  }


  @Test
  void shouldGetUserWithAuthToken() {
    String token = getToken();

    Response response = getPostResponse(token);

    User user = response.as(User.class);
    assertNotNull(user);
    int statusCode = response.getStatusCode();

    User userEntity = userProfileRepository.findByUserId(user.getUserId());

    assertNotNull(userEntity.getUserPhoneNumber());

    Response response1 =given()
            .header("Authorization", "Bearer " + token)
            .contentType("application/json")
            .when()
            .get("/api/user/" + user.getUserId());
    User user1 = response.as(User.class);
    assertNotNull(user1);
    int statusCode1 = response1.getStatusCode();
    assert(200 == statusCode);
  }




  @Test
  void shouldPostUserWithAuthToken() {
    String token = getToken();

    Response response = getPostResponse(token);

    User user = response.as(User.class);
    assertNotNull(user);
    int statusCode = response.getStatusCode();
    assert(200 == statusCode);
  }


/*
  @Test
  void shouldGetUnauthorizedWhenCreateProductWithoutAuthToken() {
    given()
      .contentType("application/json")
      .body(
        """
            {
                "title": "New Product",
                "description": "Brand New Product"
            }
        """
      )
      .when()
      .post("/api/products")
      .then()
      .statusCode(401);
  }

  @Test
  void shouldCreateProductWithAuthToken() {
    String token = getToken();

    given()
      .header("Authorization", "Bearer " + token)
      .contentType("application/json")
      .body(
        """
            {
                "title": "New Product",
                "description": "Brand New Product"
            }
        """
      )
      .when()
      .post("/api/products")
      .then()
      .statusCode(201);
  }*/

  private String getToken() {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.put("grant_type", singletonList(GRANT_TYPE_CLIENT_CREDENTIALS));
    map.put("client_id", singletonList(CLIENT_ID));
    map.put("username", singletonList(USERNAME));
    map.put("password", singletonList(PASSWORD));
//TODO change it to working openid token endpoint
    String authServerUrl ="http://localhost:8080/realms/expenses/protocol/openid-connect/token";
     /* oAuth2ResourceServerProperties.getJwt().getIssuerUri() +
      "/protocol/openid-connect/token";*/

    var request = new HttpEntity<>(map, httpHeaders);
    KeyCloakToken token = restTemplate.postForObject(
      authServerUrl,
      request,
      KeyCloakToken.class
    );

    assert token != null;
    return token.accessToken();
  }

  record KeyCloakToken(@JsonProperty("access_token") String accessToken) {}
}
