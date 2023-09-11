package com.banquemisr.currencyconversionapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * DTO for API Response used in comparison
 * @param result result status coming from the API
 * @param baseCode base currency code
 * @param targetCodes target currencies' code
 * @param conversionRates conversion rates
 * @author Muhammad Bassiouni
 * @author Menna Moataz
 */
@Builder
public record ComparisonDTO(
    @JsonProperty("result")
    String result,
    @JsonProperty("base_code")
    String baseCode,
    @JsonProperty("target_codes")
    Set<String> targetCodes,
    @JsonProperty("conversion_rates")
    Map<String, Double> conversionRates
) implements Serializable {
}
