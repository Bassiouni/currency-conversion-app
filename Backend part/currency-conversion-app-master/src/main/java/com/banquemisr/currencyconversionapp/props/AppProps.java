package com.banquemisr.currencyconversionapp.props;

import com.banquemisr.currencyconversionapp.dto.CurrencyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * This class is used to read currency list from props file
 * (application.yml) using method <code>AppProps.getCurrencies</code>
 * @author Muhammad Bassiouni
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "available")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppProps {
    private Set<CurrencyDTO> currencies;
}
