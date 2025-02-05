# Daifin Currency API

Daifin Currency API is a Spring Boot-based application designed to manage products and convert product prices between different currencies. The application provides endpoints for creating, retrieving, and converting product prices.

## Features

- **Product Management**: Create, retrieve, and list products.
- **Currency Conversion**: Convert product prices between different currencies using an external API (VATComply).
- **Product Entity**: Each product has a name, category, price, currency, EAN, and ASIN.

## Technologies Used

- Java 17+
- Spring Boot
- JPA (Java Persistence API)
- RESTful API
- Spring Data JPA
- External API for currency conversion (VATComply)


## Setup Instructions
- Download and install the appropriate version of PostgreSQL for your system from this link or official website
- https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

#### Prerequisites:
- Recommended IDE: IntelliJ IDEA
- OpenJDK 23
- Maven
- Jackson Databind
- PostgreSQL JDBC Driver
- Spring Boot 3.4.1 and it's these dependencies:
- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- Spring Boot DevTools
- Spring Boot Starter Test

#### Steps to Run the Application:
1. Clone this repository:
   git clone https://github.com/metetrkn/daifin-currency-api.git
2. Navigate to the project directory:
   cd daifin-currency-api
3. Build the project:
   mvn clean install
4. Run the application:
   mvn spring-boot:run

- The application should now be running at http://localhost:8080.

## Usage Instructions
1. POSTMAN can be used to test the API
2. Launch Postman. 
3. Set the base URL + extensions based on CRUD operations
4. Send the HTTP request
5. View the response in the Postman interface.

## API Endpoints
- **Base URL**: `http://localhost:8080`

### Create a Product

- **URL**: `/api/products`
- **Method**: `POST`
- **Request Body sample:**:
  ```json
  {
    "name": "Product Name",
    "category": "Product Category",
    "currency": "USD",
    "price": 100.00,
    "ean": "1234567890123",
    "asin": "B07XYZ1234"
  }

### Response:
```json
  {
    "id": 1,
    "name": "Product Name",
    "category": "Product Category",
    "currency": "USD",
    "price": 100.00,
    "ean": "1234567890123",
    "asin": "B07XYZ1234"
  }
```

### Get a Product by ID
- **URL**: `/api/products/{id}`
- **Method**: `GET`
- **Response sample:**:
```json
  {
    "id": 1,
    "name": "Product Name",
    "category": "Product Category",
    "currency": "USD",
    "price": 100.00,
    "ean": "1234567890123",
    "asin": "B07XYZ1234"
  }
```

### Get All Products
- **URL**: `/api/products`
- **Method**: `GET`
- **Response sample:**
```json
[
    {
        "id": 1,
        "name": "Product Name",
        "category": "Product Category",
        "currency": "USD",
        "price": 100.00,
        "ean": "1234567890123",
        "asin": "B07XYZ1234"
    },

    {
        "id": 2,
        "name": "Another Product",
        "category": "Product Category",
        "currency": "EUR",
        "price": 50.00,
        "ean": "1234567890124",
        "asin": "B07XYZ1235"
    }
]
```

### Get Products by Category
- **URL**: `/api/products/category?category={nameOfCategory}}`
- **Method**: `GET`
- **Response sample:**
```json
[
      {
        "id": 28,
        "name": "Wireless Headphones",
        "category": "Electronics",
        "currency": "USD",
        "price": 150.0,
        "ean": "9876543210123",
        "asin": "B08CXYZ456"
      },

      {
        "id": 45,
        "name": "Smartphone",
        "category": "Electronics",
        "currency": "USD",
        "price": 799.99,
        "ean": "1234567890012",
        "asin": "B09ABC7890"
      }
]
```

### Delete a product by ID
- **URL**: `/api/products/{id}`
- **Method**: `DELETE`
- **Response sample:**:
```
Product with ID 9 - ({theProductName}) has been deleted successfully.
```

### Delete all products
- **URL**: `/api/products`
- **Method**: `DELETE`
- **Response sample:**:
```
All products have been deleted successfully.
```


### Update the existing product
- **URL**: `/api/products/{id}`
- **Method**: `UPDATE`
- - **Request sample: (New products information in JSON format)** 

```json
  {
    "name": "Wireless Headphones",
    "category": "Electronics",
    "currency": "USD",
    "price": 150.0,
    "ean": "9876543210123",
    "asin": "B08CXYZ456"
  }
```
- **Response sample:**
- ```Product with ID {IdNumber} has been updated successfully.```



### Convert Product Price to Another Currency
- **URL**: `/api/products/{id}/price?currency={currency}`
- **Method**: `GET`
- **Example Request**: `/api/products/1/price?currency=EUR`
- **Response sample:**
  85.00
- Some sample currency abbreviations to use in url: EUR (Euro), USD (United States Dollar), JPY (Japanese Yen),GBP (British Pound),AUD (Australian Dollar),CAD (Canadian Dollar)
  CHF (Swiss Franc)

### External API:
The currency conversion relies on an external API service from VATComply. Ensure that the service is accessible during usage.

## License:
This project is licensed under the [MIT License](LICENSE).