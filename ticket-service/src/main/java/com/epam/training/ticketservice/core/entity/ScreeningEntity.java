package com.epam.training.ticketservice.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private RoomEntity room;
    @DateTimeFormat(pattern = "YYYY-MM-DD hh:nn")
    @Column(name = "screening time")
    private Date time;

}