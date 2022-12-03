package com.epam.training.ticketservice;

import com.epam.training.ticketservice.core.services.service.MovieService;
import com.epam.training.ticketservice.core.services.service.RoomService;
import com.epam.training.ticketservice.core.services.service.ScreeningService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}

