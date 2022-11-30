package com.epam.training.ticketservice.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Movies")
@Data
@NoArgsConstructor
public class MovieEntity {
    @Id
    private String title;
    private String genre;
    private Integer length;
}
