package com.epam.training.ticketservice.core.configuration;
import com.epam.training.ticketservice.core.dateFormatter.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public DateFormatter dateFormatter(){
        return new DateFormatter();
    }
}
