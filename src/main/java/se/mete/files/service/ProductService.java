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


    /**
     * Retrieves all products by category.
     *
     * @param category The category to filter by.
     * @return A list of products in the given category.
     */
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category); // Uses repository method to find products by category
    }


    /**
     * Deletes all products from the database.
     */
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    /**
     * Checks if there are no products in the database.
     *
     * @return True if no products exist, false otherwise.
     */
    public boolean hasNoProducts() {
        return productRepository.count() == 0;
    }


    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     * @return True if the product was deleted, false if not found.
     */
    public boolean deleteProductById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Checks if a product exists by its ID.
     *
     * @param id The product ID.
     * @return True if the product exists, false otherwise.
     */
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }


    /**
     * Updates an existing product in the database.
     *
     * @param updatedProduct The updated product object.
     * @return The updated product.
     */
    public Product updateProduct(Product updatedProduct) {
        return productRepository.save(updatedProduct); // Uses JpaRepository's save method which also handles update
    }
}
