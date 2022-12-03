package com.epam.training.ticketservice.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Builder(setterPrefix = "with")
@Table(name="screenings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private MovieEntity movie;
    @ManyToOne
    private RoomEntity room;
    @DateTimeFormat(pattern = "YYYY-MM-DD hh:nn")
    @Column(name = "screening_time")
    private Date time;

}