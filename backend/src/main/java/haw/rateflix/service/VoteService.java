package haw.rateflix.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import haw.rateflix.repository.ContentRepository;

/**
 * Service to manage vote counts in Redis.
 */
@Service
public class VoteService {

    private final StringRedisTemplate redisTemplate;
    private final ContentRepository contentRepository;

    public VoteService(StringRedisTemplate redisTemplate, ContentRepository contentRepository) {
        this.redisTemplate = redisTemplate;
        this.contentRepository = contentRepository;
    }

    private String getUpVoteKey(Long contentId) {
        return "content:" + contentId + ":upvotes";
    }

    private String getDownVoteKey(Long contentId) {
        return "content:" + contentId + ":downvotes";
    }

    /**
     * Increments the upvote count for a given content.
     * 
     * @param contentId The ID of the content.
     */
    public void upVote(Long contentId) {
        redisTemplate.opsForValue().increment(getUpVoteKey(contentId));
    }

    /**
     * Increments the downvote count for a given content.
     * 
     * @param contentId The ID of the content.
     */
    public void downVote(Long contentId) {
        redisTemplate.opsForValue().increment(getDownVoteKey(contentId));
    }

    /**
     * Decrements the upvote count for a given content.
     * 
     * @param contentId The ID of the content.
     */
    public void removeUpVote(Long contentId) {
        redisTemplate.opsForValue().decrement(getUpVoteKey(contentId));
    }

    /**
     * Decrements the downvote count for a given content.
     * 
     * @param contentId The ID of the content.
     */
    public void removeDownVote(Long contentId) {
        redisTemplate.opsForValue().decrement(getDownVoteKey(contentId));
    }

    /**
     * Retrieves the upvote count for a given content from Redis.
     * If Redis doesn't have the data, initializes from database.
     * 
     * @param contentId The ID of the content.
     * @return The number of upvotes.
     */
    public int getUpVotes(Long contentId) {
        String upVoteKey = getUpVoteKey(contentId);
        String value = redisTemplate.opsForValue().get(upVoteKey);
        
        if (value == null) {
            // Redis doesn't have this key, initialize from database
            return contentRepository.findById(contentId)
                .map(content -> {
                    int dbUpVotes = content.getUpVote();
                    // Set the value in Redis for future use
                    redisTemplate.opsForValue().set(upVoteKey, String.valueOf(dbUpVotes));
                    return dbUpVotes;
                })
                .orElse(0);
        }
        
        return Integer.parseInt(value);
    }

    /**
     * Retrieves the downvote count for a given content from Redis.
     * If Redis doesn't have the data, initializes from database.
     * 
     * @param contentId The ID of the content.
     * @return The number of downvotes.
     */
    public int getDownVotes(Long contentId) {
        String downVoteKey = getDownVoteKey(contentId);
        String value = redisTemplate.opsForValue().get(downVoteKey);
        
        if (value == null) {
            // Redis doesn't have this key, initialize from database
            return contentRepository.findById(contentId)
                .map(content -> {
                    int dbDownVotes = content.getDownVote();
                    // Set the value in Redis for future use
                    redisTemplate.opsForValue().set(downVoteKey, String.valueOf(dbDownVotes));
                    return dbDownVotes;
                })
                .orElse(0);
        }
        
        return Integer.parseInt(value);
    }
}