package com.epam.training.ticketservice.ui.configuration;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

@Configuration
public class PromptConfiguration implements PromptProvider{

    @Override
    public AttributedString getPrompt(){
        return new AttributedString("Ticket service>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }
}