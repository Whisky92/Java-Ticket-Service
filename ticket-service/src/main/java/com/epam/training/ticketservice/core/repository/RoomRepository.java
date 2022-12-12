package com.epam.training.ticketservice.core.repository;

import com.epam.training.ticketservice.core.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, String> {

    Optional<RoomEntity> findByName(String name);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE RoomEntity re "
           + "SET re.rowCount = :rowCount, re.columnCount = :columnCount WHERE re.name = :name")
    int updateRoom(@Param("name") String name, @Param("rowCount") int rowCount, @Param("columnCount") int columnCount);

    @Transactional
    @Modifying
    int deleteByName(String name);
}
