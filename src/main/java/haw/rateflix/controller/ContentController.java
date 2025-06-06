package haw.rateflix.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import haw.rateflix.domain.Content;
import haw.rateflix.domain.Kind;
import haw.rateflix.service.ContentService;


@RestController
@RequestMapping("/api/contents")
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public ResponseEntity<Page<Content>> getAllContents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "year") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Kind kind){
        Page<Content> contents = contentService.findWithFilters(
            search, kind, page, size, sortBy, sortDirection);
        
        return ResponseEntity.ok(contents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable Integer id) {
        return contentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Content> createContent(@RequestBody Content content) {
        Content created = contentService.save(content);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable Integer id, @RequestBody Content contentDetails) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Integer id) {
        return contentService.findById(id)
                .map(_ -> {
                    contentService.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<Void> addUpVote(@PathVariable Long id) {
        try { 
            Content content = contentService.findById(id).get();
            content.setUpVote(content.getUpVote() + 1);
            contentService.save(content);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/downvote")
    public ResponseEntity<Void> addDownVote(@PathVariable Long id) {
        try { 
            Content content = contentService.findById(id).get();
            content.setDownVote(content.getDownVote() + 1);
            contentService.save(content);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/upvote")
    public ResponseEntity<Void> removeUpVote(@PathVariable Long id) {
        try { 
            Content content = contentService.findById(id).get();
            content.setUpVote(content.getUpVote() - 1);
            contentService.save(content);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/downvote")
    public ResponseEntity<Void> removeDownVote(@PathVariable Long id) {
        try { 
            Content content = contentService.findById(id).get();
            content.setDownVote(content.getDownVote() - 1);
            contentService.save(content);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
