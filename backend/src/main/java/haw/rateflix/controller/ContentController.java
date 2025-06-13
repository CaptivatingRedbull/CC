package haw.rateflix.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import haw.rateflix.domain.Content;
import haw.rateflix.domain.Kind;
import haw.rateflix.service.ContentService;
import haw.rateflix.service.VoteService;

/**
 * Controller for managing content-related operations.
 * Provides endpoints to create, read, update, and delete content,
 * as well as to manage upvotes and downvotes.
 */
@RestController
@RequestMapping("/api/contents")
public class ContentController {
    private final ContentService contentService;
    private final VoteService voteService;

    public ContentController(ContentService contentService, VoteService voteService) {
        this.contentService = contentService;
        this.voteService = voteService;
    }

    /**
     * Retrieves a paginated list of contents with optional filters.
     *
     * @param page          the page number to retrieve (default is 0)
     * @param size          the number of items per page (default is 10)
     * @param sortBy        the field to sort by (default is "year")
     * @param sortDirection the direction of sorting (default is "desc")
     * @param search        optional search term to filter contents
     * @param kind          optional kind to filter contents
     * @return a paginated list of contents
     */
    @GetMapping
    public ResponseEntity<Page<Content>> getAllContents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "year") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Kind kind) {
        Page<Content> contents = contentService.findWithFilters(
                search, kind, page, size, sortBy, sortDirection);

        return ResponseEntity.ok(contents);
    }

    /**
     * Retrieves a content by its ID.
     *
     * @param id the ID of the content to retrieve
     * @return the content if found, or a 404 Not Found response
     */
    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable Long id) {
        return contentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new content.
     *
     * @param content the content to create
     * @return the created content
     */
    @PostMapping
    public ResponseEntity<Content> createContent(@RequestBody Content content) {
        Content created = contentService.save(content);
        return ResponseEntity.ok(created);
    }

    /**
     * Updates an existing content by its ID.
     *
     * @param id             the ID of the content to update
     * @param contentDetails the new details for the content
     * @return the updated content if found, or a 404 Not Found response
     */
    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable Long id, @RequestBody Content contentDetails) {
        return contentService.findById(id)
                .map(existing -> {
                    existing.setKind(contentDetails.getKind());
                    existing.setTitle(contentDetails.getTitle());
                    existing.setDescription(contentDetails.getDescription());
                    existing.setYear(contentDetails.getYear());
                    Content updated = contentService.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes a content by its ID.
     *
     * @param id the ID of the content to delete
     * @return a 204 No Content response if deleted, or a 404 Not Found response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        return contentService.findById(id)
                .map(ignored -> {
                    contentService.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Adds an upvote to a content by its ID.
     *
     * @param id the ID of the content to upvote
     * @return a 200 OK response if successful
     */
    @PutMapping("/{id}/upvote")
    public ResponseEntity<Void> addUpVote(@PathVariable Long id) {
        voteService.upVote(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Adds a downvote to a content by its ID.
     *
     * @param id the ID of the content to downvote
     * @return a 200 OK response if successful
     */
    @PutMapping("/{id}/downvote")
    public ResponseEntity<Void> addDownVote(@PathVariable Long id) {
        voteService.downVote(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Removes an upvote from a content by its ID.
     *
     * @param id the ID of the content to remove the upvote from
     * @return a 200 OK response if successful
     */
    @DeleteMapping("/{id}/upvote")
    public ResponseEntity<Void> removeUpVote(@PathVariable Long id) {
        voteService.removeUpVote(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Removes a downvote from a content by its ID.
     *
     * @param id the ID of the content to remove the downvote from
     * @return a 200 OK response if successful
     */
    @DeleteMapping("/{id}/downvote")
    public ResponseEntity<Void> removeDownVote(@PathVariable Long id) {
        voteService.removeDownVote(id);
        return ResponseEntity.ok().build();
    }
}