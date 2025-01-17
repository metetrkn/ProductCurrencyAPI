package se.mete.files.controller;


import se.mete.files.entity.Product;
import se.mete.files.service.CurrencyConversionService;
import se.mete.files.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
