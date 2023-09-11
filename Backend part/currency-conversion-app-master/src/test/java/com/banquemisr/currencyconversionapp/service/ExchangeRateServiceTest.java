package com.banquemisr.currencyconversionapp.service;

import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
import com.banquemisr.currencyconversionapp.dto.ComparisonDTO;
import com.banquemisr.currencyconversionapp.dto.CurrencyConversionDTO;
import com.banquemisr.currencyconversionapp.dto.CurrencyDTO;
import com.banquemisr.currencyconversionapp.dto.UnitCurrencyConversionDTO;
import com.banquemisr.currencyconversionapp.props.AppProps;
import com.banquemisr.currencyconversionapp.validation.AmountValidation;
import com.banquemisr.currencyconversionapp.validation.CurrencyExistsValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ExchangeRateServiceTest {
    @Mock
    private ExchangeRateAPIClient exchangeRateAPIClient;
    @Mock
    private AppProps appProps;
    @Mock
    private AmountValidation amountValidation;
    @Mock
    private CurrencyExistsValidation currencyExistsValidation;

    private ExchangeRateService exchangeRateService;

    @BeforeEach
    public void setUp() {
        this.exchangeRateService = new ExchangeRateService(exchangeRateAPIClient, appProps, amountValidation, currencyExistsValidation);
    }


    @Test
    void itShouldGetAvailableCurrencies() {
        when(appProps.getCurrencies()).thenReturn(Collections.singleton(new CurrencyDTO("US Dollar", "USD", "")));

        Set<CurrencyDTO> availableCurrencies = this.exchangeRateService.getAvailableCurrencies();

        assertThat(availableCurrencies).isNotNull();
        assertThat(availableCurrencies).isNotEmpty();
        verify(appProps, times(2)).getCurrencies();
    }

    @Test
    void itShouldReturnCurrencyConversionRate() {
        // Arrange: Set up mock objects and test data
        String current = "USD";
        String target = "EUR";

        // Create an instance of UnitCurrencyConversionDTO for expected result
        UnitCurrencyConversionDTO expectedConversionDTO = UnitCurrencyConversionDTO.builder()
                .baseCode(current)
                .targetCode(target)
                .conversionRate(1.23)  // Set the expected conversion rate
                .build();

        when(exchangeRateAPIClient.getCurrencyConversion(current, target))
                .thenReturn(expectedConversionDTO);

        UnitCurrencyConversionDTO actualConversionDTO = exchangeRateService.currencyConversion(current, target);

        assertThat(actualConversionDTO).isEqualTo(expectedConversionDTO);

        verify(exchangeRateAPIClient).getCurrencyConversion(current, target);
    }


    @Test
    void itShouldReturnCurrencyConversionRateAndConversionResult() {
        String current = "EGP";
        String target = "USD";
        Double amount = 100.0;

        CurrencyConversionDTO mockCurrencyConversionDTO = CurrencyConversionDTO.builder()
                .baseCode(current)
                .targetCode(target)
                .conversionRate(1.0)
                .conversionResult(100.0)
                .build();

        when(exchangeRateAPIClient.getCurrencyConversionWithAmount(current, target, amount))
                .thenReturn(mockCurrencyConversionDTO);

        CurrencyConversionDTO currencyConversionDTO =
                exchangeRateService.currencyConversion(current, target, amount);

        // Verify method calls
        verify(exchangeRateAPIClient).getCurrencyConversionWithAmount(current, target, amount);

        assertThat(currencyConversionDTO).isNotNull();
    }

    @Test
    void itShouldCompareBetweenBaseCurrencyAndListOfCurrencies() {
        String baseCurrency = "USD";
        Set<String> targets = Set.of("EGP");
        Map<String, Double> rates = new HashMap<>();
        rates.put("EGP", 1.2);

        ComparisonDTO expected = ComparisonDTO
                .builder()
                .result("success")
                .targetCodes(targets)
                .baseCode(baseCurrency)
                .conversionRates(rates)
                .build();

        when(exchangeRateAPIClient.getCurrencyInfo(baseCurrency)).thenReturn(expected);

        ComparisonDTO actual = exchangeRateService.currencyComparison(baseCurrency, targets.stream().toList());

        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(expected);
    }
}