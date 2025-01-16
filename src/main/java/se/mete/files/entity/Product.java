package se.mete.files.entity;


import jakarta.persistence.*;

/**
 * Represents a product entity in the database.
 * This class is mapped to the "products" table.
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;

    private String name;     // Product name
    private String category; // Product category (e.g., electronics, clothing)
    private String currency; // Currency in which the price is listed (e.g., USD, EUR)
    private Double price;    // Price of the product
    private String ean;      // European Article Number (EAN) for product identification
    private String asin;     // Amazon Standard Identification Number (ASIN)

    /**
     * Default constructor (needed by JPA).
     */
    public Product() {
    }

    /**
     * Parameterized constructor to initialize all fields.
     *
     * @param name     The name of the product
     * @param category The category of the product
     * @param currency The currency of the product price
     * @param price    The price of the product
     * @param ean      The EAN code of the product
     * @param asin     The ASIN code of the product
     */
    public Product(String name, String category, String currency, Double price, String ean, String asin) {
        this.name = name;
        this.category = category;
        this.currency = currency;
        this.price = price;
        this.ean = ean;
        this.asin = asin;
    }

    // Getters and Setters (for accessing and modifying private fields)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }
}
