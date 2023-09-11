package com.banquemisr.currencyconversionapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;

/**
 * @param baseCode The currency which you want to convert from
 * @param targetCode The currency which you want to convert to
 * @param conversionRate the conversion rate
 */
@Builder
public record UnitCurrencyConversionDTO(
    @JsonProperty("base_code")
    String baseCode,
    @JsonProperty("target_code")
    String targetCode,
    @JsonProperty("conversion_rate")
    Double conversionRate
) implements Serializable {
}
