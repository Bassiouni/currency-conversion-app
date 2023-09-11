package com.banquemisr.currencyconversionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Entry point of Spring Application
 * @author Muhammad Bassiouni
 */
@SpringBootApplication
@EnableFeignClients
@EnableCaching
@EnableScheduling
public class CurrencyConversionAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionAppApplication.class, args);
    }

}
