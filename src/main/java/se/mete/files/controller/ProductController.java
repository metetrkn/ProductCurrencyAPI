package se.mete.files.controller;


import se.mete.files.entity.Product;
import se.mete.files.service.CurrencyConversionService;
import se.mete.files.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * REST controller for managing product-related operations.
 * Provides endpoints for creating, retrieving, and converting product prices.
 */
@RestController
@RequestMapping("/api/products") // Base path for all product-related endpoints
public class ProductController {

    private final ProductService productService;
    private final CurrencyConversionService currencyConversionService;

    /**
     * Constructor-based dependency injection for ProductService and CurrencyConversionService.
     *
     * @param productService Service handling product-related logic.
     * @param currencyConversionService Service handling currency conversion.
     */
    public ProductController(ProductService productService,
                             CurrencyConversionService currencyConversionService) {
        this.productService = productService;
        this.currencyConversionService = currencyConversionService;
    }

    /**
     * Saves a new product in the database.
     *
     * @param productRequest The product data received in the request body.
     * @return The saved product wrapped in a ResponseEntity.
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product productRequest) {
        Product savedProduct = productService.saveProduct(productRequest);
        return ResponseEntity.ok(savedProduct);
    }

    /**
     * Retrieves a product by its unique identifier (ID).
     *
     * @param id The product ID received as a path variable.
     * @return The product if found, otherwise a 404 Not Found response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    /**
     * Retrieves all available products from the database.
     *
     * @return A list of all stored products wrapped in a ResponseEntity.
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves the price of a product in a different currency.
     * Example request: GET /api/products/{id}/price?currency=EUR
     *
     * @param id The product ID received as a path variable.
     * @param currency The target currency received as a request parameter.
     * @return The converted price in the requested currency.
     */
    @GetMapping("/{id}/price")
    public ResponseEntity<Double> getProductPriceInCurrency(
            @PathVariable Long id,
            @RequestParam String currency
    ) {
        // Fetch the product by ID
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build(); // Return 404 if product is not found
        }

        // Get the base currency and price from the product
        String baseCurrency = product.getCurrency();
        Double originalPrice = product.getPrice();

        // Convert the price to the requested currency
        Double convertedPrice = currencyConversionService
                .convertPrice(originalPrice, baseCurrency, currency.toUpperCase());

        return ResponseEntity.ok(convertedPrice);
    }


    /**
     * Retrieves all products by category.
     * Example request: GET /api/products/category?category=electronics
     *
     * @param category The product category received as a request parameter.
     * @return A list of products in the requested category.
     */
    @GetMapping("/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam String category) {
        List<Product> products = productService.getProductsByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no products found in category
        }
        return ResponseEntity.ok(products); // Return the products found in the category
    }


    /**
     * Deletes all products from the database.
     *
     * @return A message indicating the operation was successful or that no products exist.
     */
    @DeleteMapping
    public ResponseEntity<String> deleteAllProducts() {
        if (productService.hasNoProducts()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no products in the database currently.");
        }
        productService.deleteAllProducts();
        return ResponseEntity.ok("All products have been deleted successfully.");
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The product ID received as a path variable.
     * @return A message indicating success or failure.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        // Getting product from its ID
        Product product = productService.getProductById(id);

        if (!productService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + id + " does not exist.");
        }

        productService.deleteProductById(id);
        return ResponseEntity.ok("Product with ID " + id + " - (" + product.getName() + ") has been deleted successfully.");
    }


    /**
     * Updates an existing product by ID.
     * Example request: PUT /api/products/{id}
     *
     * @param id The ID of the product to update.
     * @param updatedProduct The new product data.
     * @return Response with status 404 if the product is not found, otherwise updates the product and returns status 200.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existingProduct = productService.getProductById(id); // existingProduct to be updated
        String existingProductName = existingProduct.getName(); // existingProduct's name

        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + id + " not found.");
        }

        // Updates the existing product with the new data
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCurrency(updatedProduct.getCurrency());
        existingProduct.setEan(updatedProduct.getEan());
        existingProduct.setAsin(updatedProduct.getAsin());

        productService.saveProduct(existingProduct); // Saves the updated product back to the database

        return ResponseEntity.ok("Product with ID " + id + " - (" + existingProductName +
                ") has been updated successfully.");
    }
}
