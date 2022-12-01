package com.epam.training.ticketservice.core.repository;

import com.epam.training.ticketservice.core.entity.MovieEntity;
import com.epam.training.ticketservice.core.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity, String> {

    Optional<RoomEntity> findByName(String name);
    @Transactional
    @Modifying
    @Query("UPDATE RoomEntity re SET re.rowCount=:rowCount, re.columnCount=:columnCount WHERE re.name=:name")
    int updateRoom(String name, int rowCount, int columnCount);

    @Transactional
    @Modifying
    int deleteByName(String name);
}
