package com.epam.training.ticketservice.core.repository;

import com.epam.training.ticketservice.core.entity.ScreeningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRepository extends JpaRepository<ScreeningEntity, Integer> {

}
