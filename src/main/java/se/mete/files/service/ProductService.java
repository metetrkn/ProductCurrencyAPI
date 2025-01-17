package se.mete.files.service;


import se.mete.files.entity.Product;
import se.mete.files.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing product-related business logic.
 * Handles CRUD operations and acts as an intermediary between the controller and the repository.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Constructor-based dependency injection to inject the ProductRepository.
     *
     * @param productRepository The repository used for interacting with the database.
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Saves a new product to the database.
     *
     * @param product The product to be saved.
     * @return The saved product.
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product); // Uses JpaRepository's save method
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The unique identifier of the product.
     * @return The product if found, otherwise null.
     */
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null); // Returns null if product not found (could throw exception instead)
    }

    /**
     * Retrieves all products in the database.
     *
     * @return A list of all products.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll(); // Fetches all products from the database
    }
}
