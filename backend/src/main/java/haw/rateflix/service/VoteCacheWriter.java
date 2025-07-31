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
        // Get all content IDs that have vote data in Redis
        Set<String> upVoteKeys = redisTemplate.keys("content:*:upvotes");
        Set<String> downVoteKeys = redisTemplate.keys("content:*:downvotes");
        
        Set<Long> contentIds = new java.util.HashSet<>();
        
        // Collect all content IDs from upvote keys
        if (upVoteKeys != null) {
            for (String key : upVoteKeys) {
                Long contentId = Long.parseLong(key.split(":")[1]);
                contentIds.add(contentId);
            }
        }
        
        // Collect all content IDs from downvote keys
        if (downVoteKeys != null) {
            for (String key : downVoteKeys) {
                Long contentId = Long.parseLong(key.split(":")[1]);
                contentIds.add(contentId);
            }
        }
        
        int updatedCount = 0;
        // Update both upvotes and downvotes for each content in a single operation
        for (Long contentId : contentIds) {
            boolean wasUpdated = contentRepository.findById(contentId)
                .map(content -> {
                    // Get current vote counts from Redis
                    String upVotesStr = redisTemplate.opsForValue().get("content:" + contentId + ":upvotes");
                    String downVotesStr = redisTemplate.opsForValue().get("content:" + contentId + ":downvotes");
                    
                    int redisUpVotes = upVotesStr != null ? Integer.parseInt(upVotesStr) : 0;
                    int redisDownVotes = downVotesStr != null ? Integer.parseInt(downVotesStr) : 0;
                    
                    // Only update if the Redis values are different from database values
                    // This prevents unnecessary database writes and ensures we don't lose data
                    if (content.getUpVote() != redisUpVotes || content.getDownVote() != redisDownVotes) {
                        content.setUpVote(redisUpVotes);
                        content.setDownVote(redisDownVotes);
                        contentRepository.save(content);
                        return true;
                    }
                    return false;
                }).orElse(false);
            
            if (wasUpdated) {
                updatedCount++;
            }
        }
        
        if (updatedCount > 0) {
            System.out.println("VoteCacheWriter: Updated vote counts for " + updatedCount + " content items.");
        }
    }
}