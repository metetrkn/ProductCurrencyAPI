package se.mete.files.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Service for converting product prices between different currencies.
 * Uses an external API (VATComply) to fetch exchange rates dynamically.
 */
@Service
public class CurrencyConversionService {

    private static final String RATES_API_URL = "https://api.vatcomply.com/rates?base="; // Base URL for currency exchange rates

    /**
     * Converts a given price from one currency to another.
     *
     * @param price The price amount to be converted.
     * @param baseCurrency The original currency of the price.
     * @param targetCurrency The currency to which the price should be converted.
     * @return The converted price in the target currency.
     */
    public Double convertPrice(Double price, String baseCurrency, String targetCurrency) {
        if (baseCurrency.equalsIgnoreCase(targetCurrency)) {
            return price; // No conversion needed if currencies are the same
        }

        RestTemplate restTemplate = new RestTemplate(); // REST client for API calls
        String requestUrl = RATES_API_URL + baseCurrency; // Construct API request URL
        // Example: https://api.vatcomply.com/rates?base=USD

        try {
            // Fetch exchange rates from the API
            Map<String, Object> response = restTemplate.getForObject(requestUrl, Map.class);
            Map<String, Double> rates = (Map<String, Double>) response.get("rates");

            if (rates.containsKey(targetCurrency)) {
                Double exchangeRate = rates.get(targetCurrency); // Retrieve exchange rate for target currency
                return price * exchangeRate; // Convert price using exchange rate
            } else {
                throw new RuntimeException("Target currency not found in rates: " + targetCurrency);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching currency rates", e);
        }
    }
}
