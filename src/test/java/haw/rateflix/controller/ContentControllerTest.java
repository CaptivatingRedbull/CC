package haw.rateflix.controller;

import haw.rateflix.domain.Content;
import haw.rateflix.domain.Kind;
import haw.rateflix.domain.User;
import haw.rateflix.repository.ContentRepository;
import haw.rateflix.repository.UserRepository;
import haw.rateflix.service.JpaUserDetailsService;
import haw.rateflix.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Content Controller Integration Tests")
class ContentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    private String baseUrl;
    private String adminUserJwt;
    private Content existingContent;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api/contents";
        contentRepository.deleteAll();
        userRepository.deleteAll();
        redisTemplate.delete(redisTemplate.keys("*"));

        // Create an admin user to perform authenticated operations
        User adminUser = new User("admin", passwordEncoder.encode("adminpass"));
        userRepository.save(adminUser);
        adminUserJwt = jwtUtil.generateToken(jpaUserDetailsService.loadUserByUsername(adminUser.getUsername()));

        // Create some initial content
        existingContent = new Content(Kind.MOVIE, "Inception", "A mind-bending thriller", 2010, 100, 10);
        contentRepository.save(existingContent);
        String upVoteKey = "content:" + existingContent.getId() + ":upvotes";
        redisTemplate.opsForValue().set(upVoteKey, "100");

    }

    private HttpHeaders getAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminUserJwt);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Test
    @DisplayName("GET /api/contents - Should return a page of contents without authentication")
    void getAllContents_shouldReturnPageOfContents() {
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).contains("Inception");
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("GET /api/contents/{id} - Should return content when ID is found")
    void getContentById_shouldReturnContent_whenFound() {
        ResponseEntity<Content> response = restTemplate.getForEntity(baseUrl + "/" + existingContent.getId(),
                Content.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTitle()).isEqualTo("Inception");
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("POST /api/contents - Should create content as authenticated admin")
    void createContent_asAdmin_shouldReturnCreatedContent() {
        // Given
        Content newContent = new Content(Kind.SERIES, "Breaking Bad", "A chemistry teacher's new career.", 2008, 500,
                5);
        HttpEntity<Content> request = new HttpEntity<>(newContent, getAuthHeaders());

        // When
        ResponseEntity<Content> response = restTemplate.postForEntity(baseUrl, request, Content.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); // Note: Your controller returns OK, not CREATED
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTitle()).isEqualTo("Breaking Bad");
        assertThat(contentRepository.count()).isEqualTo(2);
    }

    @Test
    @DisplayName("POST /api/contents - Should be Forbidden for unauthenticated user")
    void createContent_unauthenticated_shouldBeForbidden() {
        Content newContent = new Content(Kind.SERIES, "The Office", "Mockumentary", 2005, 300, 30);
        HttpEntity<Content> request = new HttpEntity<>(newContent); // No auth headers

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("PUT /api/contents/{id}/upvote - Should increment upvote and return OK")
    void addUpVote_shouldIncrementUpVote() {
        // Given
        String url = baseUrl + "/" + existingContent.getId() + "/upvote";
        HttpEntity<Void> request = new HttpEntity<>(null); // No body needed

        // When
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        HttpEntity<Content> contentResponse = restTemplate.getForEntity(baseUrl + "/" + existingContent.getId(),
                Content.class);
        assertThat(contentResponse.getBody().getUpVote()).isEqualTo(101);
    }
}
