package com.banquemisr.currencyconversionapp.controllers;

import com.banquemisr.currencyconversionapp.dto.*;
import com.banquemisr.currencyconversionapp.entities.Response;
import com.banquemisr.currencyconversionapp.service.ExchangeRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * <p>API router with prefix=/api/v1/currencies</p>
 * <p>This router is concerned with every action related to the retrieval of the currencies' data</p>
 * @author Muhammad Bassiouni
 * @author Nouran Hosameldin
 * @author Menna Moataz
 */
@RestController
@RequestMapping("api/v1/currencies")
@CrossOrigin
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    /**
     * Get available currencies which are supported by the API from
     * {@link com.banquemisr.currencyconversionapp.props.AppProps Application Properties}
     * @return Available currencies supported by the API
     * @author Menna Moataz
     * @author Muhammad Bassiouni
     */
    @GetMapping
    public ResponseEntity<Response<Set<CurrencyDTO>>> getAvailableCurrencies() {
        Set<CurrencyDTO> currencyDTOS = this.exchangeRateService.getAvailableCurrencies();

        Response<Set<CurrencyDTO>> response = new Response<>(
                HttpStatus.OK.value(),
                "success",
                "Currency list retrieved successfully",
                currencyDTOS
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Get the conversion rate between 2 currencies
     * @param current the base currency
     * @param target the target currency
     * @return {@link com.banquemisr.currencyconversionapp.entities.Response Response Entity}
     * of type {@link com.banquemisr.currencyconversionapp.dto.UnitCurrencyConversionDTO UnitCurrencyConversionDTO}
     * containing <code>base_code</code>, <code>target_code</code> and <code>conversion_rate</code>
     * @author Menna Moataz
     * @author Muhammad Bassiouni
     */
    @GetMapping("convert/{current}/{target}")
    public ResponseEntity<Response<UnitCurrencyConversionDTO>> getCurrencyConversion(
        @PathVariable("current") String current,
        @PathVariable("target") String target
    ) {
        UnitCurrencyConversionDTO currencyConversion = this.exchangeRateService.currencyConversion(current, target);

        Response<UnitCurrencyConversionDTO> response = new Response<>(
                HttpStatus.OK.value(),
                "success",
                "Currency converted successfully",
                currencyConversion
        );

        return ResponseEntity.ok(response);
    }

    /**
     * <p>Calculate the conversion result between 2 currencies with some amount
     * of the base currency using the conversion rate</p>
     * @param current the base currency
     * @param target the target currency
     * @param amount the amount to convert
     * @return {@link com.banquemisr.currencyconversionapp.entities.Response Response Entity}
     * of type {@link com.banquemisr.currencyconversionapp.dto.CurrencyConversionDTO CurrencyConversionDTO}
     * containing <code>base_code</code>, <code>target_code</code>,
     * <code>conversion_rate</code> and <code>conversion_result</code>
     * @author Nouran Hosameldin
     * @author Menna Moataz
     */
    @GetMapping("convert/{current}/{target}/{amount}")
    public ResponseEntity<Response<CurrencyConversionDTO>> getCurrencyConversionWithAmount(
        @PathVariable("current") String current,
        @PathVariable("target") String target,
        @PathVariable("amount") Double amount
    ) {
        CurrencyConversionDTO conversionDTO = this.exchangeRateService.currencyConversion(current, target, amount);

        Response<CurrencyConversionDTO> response = new Response<>(
                HttpStatus.OK.value(),
                "success",
                "Currency converted successfully with the provided amount",
                conversionDTO
        );

        return ResponseEntity.ok(response);
    }

    /**
     * <p>Compare between multiple currencies</p>
     * @param requestBody Request body of type
     * {@link
     *      com.banquemisr.currencyconversionapp.dto.CurrencyComparisonRequestBodyPOJO
     *      CurrencyComparisonRequestBodyPOJO}
     * @return {@link com.banquemisr.currencyconversionapp.entities.Response Response Entity}
     * of type {@link com.banquemisr.currencyconversionapp.dto.ComparisonDTO ComparisonDTO}
     * containing <code>conversion_rates</code> relative to <code>base_code</code>
     * @author Menna Moataz
     */
    @PostMapping("compare")
    public ResponseEntity<Response<ComparisonDTO>> getCurrencyComparison(
        @RequestBody CurrencyComparisonRequestBodyPOJO requestBody
    ) {
        ComparisonDTO comparisonDTO = this.exchangeRateService.currencyComparison(
            requestBody.baseCode(),
            requestBody.targetCodes()
        );

        Response<ComparisonDTO> response = new Response<>(
                HttpStatus.OK.value(),
                "success",
                "Comparison done successfully",
                comparisonDTO
        );

        return ResponseEntity.ok(response);
    }
}
