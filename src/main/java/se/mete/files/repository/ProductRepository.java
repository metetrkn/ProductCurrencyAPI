package se.mete.files.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.mete.files.entity.Product;

import java.util.List;

/**
 * Repository interface for Product entity.
 * Extends JpaRepository to provide built-in CRUD operations.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds all products by category.
     * This method follows Spring Data JPA naming conventions,
     * so the query is automatically generated.
     *
     * @param category The category to filter by.
     * @return A list of products in the given category.
     */
    List<Product> findByCategory(String category);
}
