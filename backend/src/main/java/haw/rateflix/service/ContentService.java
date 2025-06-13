package haw.rateflix.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import haw.rateflix.domain.Content;
import haw.rateflix.domain.Kind;
import haw.rateflix.repository.ContentRepository;
import java.util.List;
import java.util.Optional;

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
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Content> contentPage = contentRepository.findAll(pageable);
        contentPage.forEach(this::updateVotesFromCache);
        return contentPage;
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
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Content> contentPage = contentRepository.findAll(ContentSpecification.withFilters(search, kind), pageable);
        contentPage.forEach(this::updateVotesFromCache);
        return contentPage;
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
     * Updates the vote counts of a Content object from the Redis cache.
     * 
     * @param content The Content object to update.
     */
    private void updateVotesFromCache(Content content) {
        long contentId = content.getId();
        content.setUpVote(voteService.getUpVotes(contentId));
        content.setDownVote(voteService.getDownVotes(contentId));
    }
}