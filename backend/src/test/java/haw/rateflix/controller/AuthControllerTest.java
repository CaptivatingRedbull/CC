package haw.rateflix.controller;

import haw.rateflix.domain.User;
import haw.rateflix.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Authentication Controller Integration Tests")
class AuthControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api/auth";
        userRepository.deleteAll();

        // Create a pre-existing user for login tests
        User existingUser = new User("testuser", passwordEncoder.encode("password"));
        userRepository.save(existingUser);
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("POST /authenticate - Should return token for valid credentials")
    void createAuthenticationToken_shouldReturnTokenForValidCredentials() {
        // Given
        AuthController.AuthRequest authRequest = new AuthController.AuthRequest();
        authRequest.setUsername("testuser");
        authRequest.setPassword("password");
        HttpEntity<AuthController.AuthRequest> request = new HttpEntity<>(authRequest);

        // When
        ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                baseUrl + "/authenticate",
                org.springframework.http.HttpMethod.POST,
                request,
                new org.springframework.core.ParameterizedTypeReference<Map<String, String>>() {
                });

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("token")).isNotNull().isInstanceOf(String.class);
        assertThat(response.getBody().get("username")).isEqualTo("testuser");
    }

    @Test
    @DisplayName("POST /authenticate - Should return 500 for invalid credentials")
    void createAuthenticationToken_shouldFailForInvalidCredentials() {
        // Given
        AuthController.AuthRequest authRequest = new AuthController.AuthRequest();
        authRequest.setUsername("testuser");
        authRequest.setPassword("wrongpassword");
        HttpEntity<AuthController.AuthRequest> request = new HttpEntity<>(authRequest);

        // When
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/authenticate", request, String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody()).contains("Incorrect username or password");
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("POST /create-user - Should create user successfully")
    void createUser_shouldSucceedForNewUsername() {
        // Given
        AuthController.RegisterRequest registerRequest = new AuthController.RegisterRequest();
        registerRequest.setUsername("newuser");
        registerRequest.setPassword("newpassword123");

        // Authenticate as testuser to get JWT token
        AuthController.AuthRequest authRequest = new AuthController.AuthRequest();
        authRequest.setUsername("testuser");
        authRequest.setPassword("password");
        HttpEntity<AuthController.AuthRequest> authHttpEntity = new HttpEntity<>(authRequest);

        ResponseEntity<Map<String, String>> authResponse = restTemplate.exchange(
                baseUrl + "/authenticate",
                org.springframework.http.HttpMethod.POST,
                authHttpEntity,
                new org.springframework.core.ParameterizedTypeReference<Map<String, String>>() {
                });

        String token = authResponse.getBody().get("token");

        // Add Authorization header with Bearer token
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<AuthController.RegisterRequest> request = new HttpEntity<>(registerRequest, headers);

        // When
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/create-user", request, String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("User created successfully");

        // Verify user is in the database
        assertThat(userRepository.existsByUsername("newuser")).isTrue();
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("POST /create-user - Should return 400 Bad Request if username is taken")
    void createUser_shouldFailForExistingUsername() {
        // Given
        AuthController.RegisterRequest registerRequest = new AuthController.RegisterRequest();
        registerRequest.setUsername("testuser"); // This user already exists from setUp
        registerRequest.setPassword("password");

        // Authenticate as testuser to get JWT token
        AuthController.AuthRequest authRequest = new AuthController.AuthRequest();
        authRequest.setUsername("testuser");
        authRequest.setPassword("password");
        HttpEntity<AuthController.AuthRequest> authHttpEntity = new HttpEntity<>(authRequest);

        ResponseEntity<Map<String, String>> authResponse = restTemplate.exchange(
                baseUrl + "/authenticate",
                org.springframework.http.HttpMethod.POST,
                authHttpEntity,
                new org.springframework.core.ParameterizedTypeReference<Map<String, String>>() {
                });

        String token = authResponse.getBody().get("token");

        // Add Authorization header with Bearer token
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<AuthController.RegisterRequest> request = new HttpEntity<>(registerRequest, headers);

        // When
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/create-user", request, String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("Username is already taken!");
    }
}
