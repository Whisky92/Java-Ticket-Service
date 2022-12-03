package com.epam.training.ticketservice.core.repository;

import com.epam.training.ticketservice.core.dateFormatter.DateFormatter;
import com.epam.training.ticketservice.core.entity.MovieEntity;
import com.epam.training.ticketservice.core.entity.RoomEntity;
import com.epam.training.ticketservice.core.entity.ScreeningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Repository
public interface ScreeningRepository extends JpaRepository<ScreeningEntity, Integer> {
        @Modifying
        @Transactional
        int deleteByMovieAndRoomAndTime(MovieEntity movie, RoomEntity room, Date date);
    }

