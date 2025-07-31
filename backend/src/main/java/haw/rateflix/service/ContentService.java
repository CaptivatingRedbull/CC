package haw.rateflix.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import haw.rateflix.domain.Content;
import haw.rateflix.domain.Kind;
import haw.rateflix.repository.ContentRepository;
import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Service class for managing Content objects in the Rateflix application.
 * Provides methods to find, save, and delete Content objects, as well as
 * to retrieve vote counts from a VoteService.
 */
@Service
public class ContentService {
    private final ContentRepository contentRepository;
    private final VoteService voteService;

    public ContentService(ContentRepository contentRepository, VoteService voteService) {
        this.contentRepository = contentRepository;
        this.voteService = voteService;
    }

    /**
     * Finds all Content objects in a paginated manner, sorted by the specified
     * field
     * and direction.
     *
     * @param page          The page number to retrieve (0-indexed).
     * @param size          The number of items per page.
     * @param sortBy        The field to sort by.
     * @param sortDirection The direction of sorting ("asc" or "desc").
     * @return A Page containing Content objects.
     */
    public Page<Content> findAllPaginated(int page, int size, String sortBy, String sortDirection) {
        // Check if sorting is by vote-related fields
        boolean isVoteSorting = "upVote".equals(sortBy) || "downVote".equals(sortBy) || "score".equals(sortBy);
        
        if (isVoteSorting) {
            // For vote-based sorting, use the same approach as filtered search
            return findWithFiltersAndVoteBasedSorting(null, null, page, size, sortBy, sortDirection);
        } else {
            // For non-vote fields, use database sorting as before
            Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
            Page<Content> contentPage = contentRepository.findAll(pageable);
            contentPage.forEach(this::updateVotesFromCache);
            return contentPage;
        }
    }

    /**
     * Finds Content objects with optional filters for search term and kind.
     *
     * @param search        Optional search term to filter contents.
     * @param kind          Optional kind to filter contents.
     * @param page          The page number to retrieve (0-indexed).
     * @param size          The number of items per page.
     * @param sortBy        The field to sort by.
     * @param sortDirection The direction of sorting ("asc" or "desc").
     * @return A Page containing Content objects that match the filters.
     */
    public Page<Content> findWithFilters(String search, Kind kind, int page, int size, String sortBy,
            String sortDirection) {
        // Check if sorting is by vote-related fields
        boolean isVoteSorting = "upVote".equals(sortBy) || "downVote".equals(sortBy) || "score".equals(sortBy);
        
        if (isVoteSorting) {
            // For vote-based sorting, we need to get all matching content first,
            // update votes from cache, then sort in memory
            return findWithFiltersAndVoteBasedSorting(search, kind, page, size, sortBy, sortDirection);
        } else {
            // For non-vote fields, use database sorting as before
            Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
            Page<Content> contentPage = contentRepository.findAll(ContentSpecification.withFilters(search, kind), pageable);
            contentPage.forEach(this::updateVotesFromCache);
            return contentPage;
        }
    }

    /**
     * Finds all Content objects without pagination.
     *
     * @return A List of all Content objects.
     */
    public List<Content> findAll() {
        List<Content> contents = contentRepository.findAll();
        contents.forEach(this::updateVotesFromCache);
        return contents;
    }

    /**
     * Finds a Content object by its ID.
     *
     * @param id The ID of the Content object to find.
     * @return An Optional containing the Content object if found, or empty if not
     *         found.
     */
    public Optional<Content> findById(long id) {
        Optional<Content> contentOptional = contentRepository.findById(id);
        contentOptional.ifPresent(this::updateVotesFromCache);
        return contentOptional;
    }

    /**
     * Saves a Content object to the database.
     *
     * @param content The Content object to save.
     * @return The saved Content object.
     */
    public Content save(Content content) {
        return contentRepository.save(content);
    }

    /**
     * Deletes a Content object by its ID.
     *
     * @param id The ID of the Content object to delete.
     */
    public void deleteById(long id) {
        contentRepository.deleteById(id);
    }

    /**
     * Handles filtering and sorting when sorting by vote-related fields.
     * This method gets all matching content, updates votes from cache, 
     * then sorts in memory and applies pagination.
     *
     * @param search        Optional search term to filter contents.
     * @param kind          Optional kind to filter contents.
     * @param page          The page number to retrieve (0-indexed).
     * @param size          The number of items per page.
     * @param sortBy        The vote-related field to sort by.
     * @param sortDirection The direction of sorting ("asc" or "desc").
     * @return A Page containing Content objects sorted by current vote data.
     */
    private Page<Content> findWithFiltersAndVoteBasedSorting(String search, Kind kind, int page, int size, 
            String sortBy, String sortDirection) {
        // Get all matching content without pagination or sorting
        List<Content> allMatchingContent = contentRepository.findAll(ContentSpecification.withFilters(search, kind));
        
        // Update vote counts from Redis for all content
        allMatchingContent.forEach(this::updateVotesFromCache);
        
        // Sort in memory based on the requested field and direction
        Comparator<Content> comparator = getVoteComparator(sortBy);
        if ("desc".equalsIgnoreCase(sortDirection)) {
            comparator = comparator.reversed();
        }
        
        List<Content> sortedContent = allMatchingContent.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        
        // Apply pagination manually
        int start = page * size;
        int end = Math.min(start + size, sortedContent.size());
        
        List<Content> pageContent = (start < sortedContent.size()) 
                ? sortedContent.subList(start, end) 
                : List.of();
        
        return new PageImpl<>(pageContent, PageRequest.of(page, size), sortedContent.size());
    }

    /**
     * Creates a comparator for sorting by vote-related fields.
     *
     * @param sortBy The field to sort by ("upVote", "downVote", or "score").
     * @return A comparator for the specified field.
     */
    private Comparator<Content> getVoteComparator(String sortBy) {
        switch (sortBy) {
            case "upVote":
                return Comparator.comparingInt(Content::getUpVote);
            case "downVote":
                return Comparator.comparingInt(Content::getDownVote);
            case "score":
                return Comparator.comparingInt(Content::getScore);
            default:
                // Fallback to upVote if unknown
                return Comparator.comparingInt(Content::getUpVote);
        }
    }

    /**
     * Updates the vote counts of a Content object from the Redis cache.
     * 
     * @param content The Content object to update.
     */
    private void updateVotesFromCache(Content content) {
        long contentId = content.getId();
        int upVotes = voteService.getUpVotes(contentId);
        int downVotes = voteService.getDownVotes(contentId);
        content.setUpVote(upVotes);
        content.setDownVote(downVotes);
        content.setScore(upVotes - downVotes);
    }
}