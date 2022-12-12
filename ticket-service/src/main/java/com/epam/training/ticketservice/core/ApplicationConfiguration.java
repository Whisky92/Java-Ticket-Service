package com.epam.training.ticketservice.core;

import com.epam.training.ticketservice.core.dateformatter.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }

}
