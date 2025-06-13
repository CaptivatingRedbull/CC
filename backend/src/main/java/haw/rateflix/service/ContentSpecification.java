package haw.rateflix.service;

import haw.rateflix.domain.Content;
import haw.rateflix.domain.Kind;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification class for filtering Content objects based on search criteria.
 * It allows filtering by title and kind of content.
 */
public class ContentSpecification {

    public static Specification<Content> withFilters(String search, Kind kind) {
        return (root, ignored, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search.toLowerCase() + "%";
                Predicate titlePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")), searchPattern);
                predicates.add(criteriaBuilder.or(titlePredicate));
            }

            if (kind != null) {
                predicates.add(criteriaBuilder.equal(root.get("kind"), kind));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}