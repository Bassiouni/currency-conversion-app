package com.banquemisr.currencyconversionapp.service;

import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
import com.banquemisr.currencyconversionapp.dto.*;
import com.banquemisr.currencyconversionapp.exception.BadEntryException;
import com.banquemisr.currencyconversionapp.exception.NotFoundException;
import com.banquemisr.currencyconversionapp.props.AppProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableConfigurationProperties(value = AppProps.class)
public class ExchangeRateService {
    private final ExchangeRateAPIClient exchangeRateAPIClient;
    private final AppProps appProps;

    public ExchangeRateService(ExchangeRateAPIClient exchangeRateAPIClient, AppProps appProps) {
        this.exchangeRateAPIClient = exchangeRateAPIClient;
        this.appProps = appProps;
    }

    @Cacheable(value = "currencies", key = "#root.methodName")
    public Set<CurrencyDTO> getAvailableCurrencies() {
        return this.appProps.getCurrencies();
    }

    public UnitCurrencyConversionDTO currencyConversion(String current, String target) {
        return this.exchangeRateAPIClient.getCurrencyConversion(current, target);
    }

    public CurrencyConversionDTO currencyConversion(String current, String target, Double amount) {
        if (amount <= 0) {
            throw new BadEntryException("The amount should be more than 0");
        }

        return this.exchangeRateAPIClient.getCurrencyConversionWithAmount(current, target, amount);
    }

    @Cacheable(value = "currencies", key = "#root.methodName")
    public ExchangeRateDataDTO getExchangeRate(String current) {
        List<String> codes = new ArrayList<>();

        for (CurrencyDTO code : appProps.getCurrencies()) {
            codes.add(code.code());
        }

        Optional<String> expectedCurrency = codes.stream().filter(code -> Objects.equals(code, current)).findFirst();

        if (expectedCurrency.isEmpty()) {
            throw new NotFoundException("Currency not found.");
        }

        return this.exchangeRateAPIClient.getCurrencyInfo(current);
    }

    public ExchangeRateDataDTO currencyComparison(String current, List<String> targets) {
        ExchangeRateDataDTO response = exchangeRateAPIClient.getCurrencyInfo(current);

        List<String> codes = new ArrayList<>();

        for (CurrencyDTO code : appProps.getCurrencies()) {
            codes.add(code.code());
        }

        Optional<String> expectedCurrency = codes.stream().filter(code -> Objects.equals(code, current)).findFirst();

        List<String> filteredList = targets.stream().filter(codes::contains).toList();

        if (filteredList.size() != targets.size() || expectedCurrency.isEmpty()) {
            throw new NotFoundException("Currency not found.");
        }

        if (response.result().equals("success")) {
            Map<String, Double> filteredConversionRates = new HashMap<>();
            String baseCode = response.base_code();
            Map<String, Double> conversionRates = response.conversion_rates();

            for (String target : targets) {
                if (conversionRates.containsKey(target)) {
                    filteredConversionRates.put(target, conversionRates.get(target));
                }
            }

            return ExchangeRateDataDTO
                .builder()
                .result("success")
                .base_code(baseCode)
                .target_codes(filteredConversionRates.keySet().stream().collect(Collectors.toList()))
                .conversion_rates(filteredConversionRates)
                .build();
        }

        return ExchangeRateDataDTO
            .builder()
            .result("failure")
            .build();
    }
}
