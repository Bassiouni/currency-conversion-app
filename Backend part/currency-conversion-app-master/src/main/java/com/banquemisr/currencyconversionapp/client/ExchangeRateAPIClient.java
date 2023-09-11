package com.banquemisr.currencyconversionapp.client;

import com.banquemisr.currencyconversionapp.dto.CurrencyConversionDTO;
import com.banquemisr.currencyconversionapp.dto.ComparisonDTO;
import com.banquemisr.currencyconversionapp.dto.UnitCurrencyConversionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * OpenFeign client for the external API
 * @author Muhammad Bassiouni
 */
@FeignClient(name = "ExchangeRateAPI", url = "${external-api}")
public interface ExchangeRateAPIClient {
    /**
     * <p>Get currency info from the API with exchange rates to all available currencies in the external API</p>
     * @param currency_code base currency code
     * @return Currency info with exchange rates to all available currencies of type
     * {@link com.banquemisr.currencyconversionapp.dto.ComparisonDTO ComparisonDTO}
     * @author Muhammad Bassiouni
     */
    @GetMapping("latest/{currency_code}")
    ComparisonDTO getCurrencyInfo(@PathVariable("currency_code") String currency_code);

    /**
     * <p>Get currency conversion rate between 2 currencies</p>
     * @param current base currency code
     * @param target target currency code
     * @return {@link com.banquemisr.currencyconversionapp.dto.UnitCurrencyConversionDTO UnitCurrencyConversionDTO}
     * containing <code>base_code</code>, <code>target_code</code> and <code>conversion_rate</code>
     * @author Muhammad Bassiouni
     */
    @GetMapping("pair/{current}/{target}")
    UnitCurrencyConversionDTO getCurrencyConversion(
        @PathVariable("current") String current,
        @PathVariable("target") String target
    );

    /**
     * <p>Get currency conversion rate and conversion result between 2 currencies</p>
     * @param current base currency code
     * @param target target currency code
     * @param amount amount to convert
     * @return {@link com.banquemisr.currencyconversionapp.dto.CurrencyConversionDTO CurrencyConversionDTO}
     * containing <code>base_code</code>, <code>target_code</code>,
     * <code>conversion_rate</code> and <code>conversion_result</code>
     * @author Nouran Hosameldin
     */
    @GetMapping("pair/{current}/{target}/{amount}")
    CurrencyConversionDTO getCurrencyConversionWithAmount(
        @PathVariable("current") String current,
        @PathVariable("target") String target,
        @PathVariable("amount") Double amount
    );

}
