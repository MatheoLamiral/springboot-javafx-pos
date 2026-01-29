package com.cgelectrosound.gestion.persistence;

import com.cgelectrosound.gestion.persistence.entities.Product;
import com.cgelectrosound.gestion.persistence.filters.ProductFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class Specifications {

    public static Specification<Product> getProductSpecification(ProductFilter filter) {
        return ((root, query, cb) -> {
            var predicates = cb.conjunction();

            if (filter.getName() != null && !filter.getName().isBlank()) {
                String searchTerm = "%" + filter.getName().toLowerCase() + "%";

                Predicate nameMatch = cb.like(cb.lower(root.get("name")), searchTerm);
                Predicate descMatch = cb.like(cb.lower(root.get("description")), searchTerm);

                predicates = cb.and(predicates, cb.or(nameMatch, descMatch));
            }
            if (filter.getDescription() != null) {
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("description")), "%" + filter.getDescription().toLowerCase() + "%"));
            }
            if (filter.getMinPrice() != null) {
                predicates = cb.and(predicates, cb.ge(root.get("price"), filter.getMinPrice()));
            }
            if (filter.getMaxPrice() != null) {
                predicates = cb.and(predicates, cb.le(root.get("price"), filter.getMaxPrice()));
            }
            if (filter.getMinStock() != 0) {
                predicates = cb.and(predicates, cb.ge(root.get("actualStock"), filter.getMinStock()));
            }
            return predicates;
        });
    }
}
