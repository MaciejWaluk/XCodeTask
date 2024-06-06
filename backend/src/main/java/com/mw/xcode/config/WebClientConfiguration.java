package com.mw.xcode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfiguration {

    @Bean
    public WebClient localApiClient() {
        return WebClient.create("http://api.nbp.pl/api/exchangerates/rates/A");
    }
}
