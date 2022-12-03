package com.epam.training.ticketservice.core.model;

import lombok.Value;

@Value
public class MovieDTO {

    private final String title;

    private final String genre;

    private final int length;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String title;
        private String genre;
        private Integer length;

        public Builder withTitle(String title){
            this.title=title;
            return this;
        }

        public Builder withGenre(String genre){
            this.genre=genre;
            return this;
        }

        public Builder withLength(int length){
            this.length=length;
            return this;
        }

        public MovieDTO build() { return new MovieDTO(title, genre, length);}
    }
}
