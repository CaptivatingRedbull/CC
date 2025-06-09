package haw.rateflix.service;

import haw.rateflix.repository.ContentRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Set;

/**
 * Periodically writes vote counts from Redis cache to the database.
 */
@Component
public class VoteCacheWriter {

    private final StringRedisTemplate redisTemplate;
    private final ContentRepository contentRepository;

    public VoteCacheWriter(StringRedisTemplate redisTemplate, ContentRepository contentRepository) {
        this.redisTemplate = redisTemplate;
        this.contentRepository = contentRepository;
    }

    /**
     * A scheduled task that runs every minute to write vote counts to the database.
     */
    @Scheduled(fixedRate = 60000) // Every 60 seconds
    public void writeVotesToDb() {
        // Process upvotes
        Set<String> upVoteKeys = redisTemplate.keys("content:*:upvotes");
        if (upVoteKeys != null) {
            for (String key : upVoteKeys) {
                Long contentId = Long.parseLong(key.split(":")[1]);
                String upVotesStr = redisTemplate.opsForValue().get(key);
                if (upVotesStr != null) {
                    int upVotes = Integer.parseInt(upVotesStr);
                    contentRepository.findById(contentId).ifPresent(content -> {
                        content.setUpVote(upVotes);
                        contentRepository.save(content);
                    });
                }
            }
        }

        // Process downvotes
        Set<String> downVoteKeys = redisTemplate.keys("content:*:downvotes");
        if (downVoteKeys != null) {
            for (String key : downVoteKeys) {
                Long contentId = Long.parseLong(key.split(":")[1]);
                String downVotesStr = redisTemplate.opsForValue().get(key);
                if (downVotesStr != null) {
                    int downVotes = Integer.parseInt(downVotesStr);
                    contentRepository.findById(contentId).ifPresent(content -> {
                        content.setDownVote(downVotes);
                        contentRepository.save(content);
                    });
                }
            }
        }
    }
}