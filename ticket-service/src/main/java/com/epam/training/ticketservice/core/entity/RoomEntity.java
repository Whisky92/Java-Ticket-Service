package com.epam.training.ticketservice.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {
    @Id
    private String name;
    private Integer rowCount;
    private Integer columnCount;
}
