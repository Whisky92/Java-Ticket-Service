package com.epam.training.ticketservice.ui.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

@ShellComponent
public class SystemCommand implements Quit.Command {
    @SuppressWarnings("unused")
    @ShellMethod(key = "exit", value = "Terminates the application process.")
    public void exit() {
        System.exit(0);
    }

}
