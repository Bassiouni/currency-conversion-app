// Nouran

package com.banquemisr.currencyconversionapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;

/** 
 * DTO for API Response used in Conversion
 * @param baseCode base currency code
 * @param targetCode target currency code
 * @param conversionRate the conversion rate
 * @param conversionResult the conversion result
 * @author Nouran Hossam
*/
@Builder
public record CurrencyConversionDTO(
	@JsonProperty("base_code")
	String baseCode,
	@JsonProperty("target_code")
	String targetCode,
	@JsonProperty("conversion_rate")
	Double conversionRate,
	@JsonProperty("conversion_result")
	Double conversionResult
) implements Serializable {
}
