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

@Service
public class ContentService {
    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Page<Content> findAllPaginated(int page,
            int size,
            String sortBy,
            String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return contentRepository.findAll(pageable);
    }

    public Page<Content> findWithFilters(String search,
            Kind kind,
            int page,
            int size,
            String sortBy,
            String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        return contentRepository.findAll(
                ContentSpecification.withFilters(search, kind),
                pageable);
    }

    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    public Optional<Content> findById(long id) {
        return contentRepository.findById(id);
    }

    public Content save(Content content) {
        return contentRepository.save(content);
    }

    public void deleteById(long id) {
        contentRepository.deleteById(id);
    }

}
