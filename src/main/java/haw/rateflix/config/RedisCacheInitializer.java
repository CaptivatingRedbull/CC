package haw.rateflix.config;

import haw.rateflix.domain.Content;
import haw.rateflix.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Initializes the Redis cache with vote data from the database on application
 * startup.
 */
@Component
public class RedisCacheInitializer implements CommandLineRunner {

    private final ContentRepository contentRepository;
    private final StringRedisTemplate redisTemplate;

    public RedisCacheInitializer(ContentRepository contentRepository, StringRedisTemplate redisTemplate) {
        this.contentRepository = contentRepository;
        this.redisTemplate = redisTemplate;
    }

    /**
     * This method runs after the application context is loaded and initializes the
     * Redis cache
     * with the upvote and downvote counts for each content item from the database.
     * It sets the initial values in Redis only if the keys do not already exist,
     * preventing
     * overwriting fresh votes if the server restarts quickly.
     *
     * @param args Command line arguments (not used).
     */
    @Override
    public void run(String... args) throws Exception {
        List<Content> allContent = contentRepository.findAll();

        for (Content content : allContent) {
            String upVoteKey = "content:" + content.getId() + ":upvotes";
            String downVoteKey = "content:" + content.getId() + ":downvotes";

            // Set the initial value in Redis only if the key doesn't already exist.
            // This prevents overwriting fresh votes if the server restarts quickly.
            redisTemplate.opsForValue().setIfAbsent(upVoteKey, String.valueOf(content.getUpVote()));
            redisTemplate.opsForValue().setIfAbsent(downVoteKey, String.valueOf(content.getDownVote()));
        }
    }
}