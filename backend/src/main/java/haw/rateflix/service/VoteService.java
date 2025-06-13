package haw.rateflix.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Service to manage vote counts in Redis.
 */
@Service
public class VoteService {

    private final StringRedisTemplate redisTemplate;

    public VoteService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
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
     * 
     * @param contentId The ID of the content.
     * @return The number of upvotes.
     */
    public int getUpVotes(Long contentId) {
        String value = redisTemplate.opsForValue().get(getUpVoteKey(contentId));
        return value == null ? 0 : Integer.parseInt(value);
    }

    /**
     * Retrieves the downvote count for a given content from Redis.
     * 
     * @param contentId The ID of the content.
     * @return The number of downvotes.
     */
    public int getDownVotes(Long contentId) {
        String value = redisTemplate.opsForValue().get(getDownVoteKey(contentId));
        return value == null ? 0 : Integer.parseInt(value);
    }
}