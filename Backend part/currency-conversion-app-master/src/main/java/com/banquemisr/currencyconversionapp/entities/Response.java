package com.banquemisr.currencyconversionapp.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
/**
 * shows how the response would look like
 * @param statusCode shows the response state number
 * @param status shows the response state
 * @param message response message
 * @param data the body that carries the response data
 * @author Menna Moataz
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Response<T>(
    @JsonProperty("status_code")
    Integer statusCode,
    String status,
    String message,
    T data
) {
}
