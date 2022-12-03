package com.epam.training.ticketservice.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Builder(setterPrefix = "with")
@Table(name = "screenings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MovieEntity movie;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RoomEntity room;
    @DateTimeFormat(pattern = "YYYY-MM-DD hh:nn")
    @Column(name = "screening_time")
    private Date time;

}