package com.banquemisr.currencyconversionapp.dto;

import com.banquemisr.currencyconversionapp.controllers.ExchangeRateController;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Simple POJO to receive the `Request Body` in the controller <br />
 * See {@link ExchangeRateController ExchangeRateController}
 * for more information
 * @param baseCode The currency which you want to convert from
 * @param targetCodes The currency which you want to convert to
 * @author Menna Moataz
 */
@Builder
public record CurrencyComparisonRequestBodyPOJO(
    @JsonProperty("base_code")
    String baseCode,
    @JsonProperty("target_codes")
    List<String> targetCodes
) implements Serializable {
}
