package com.epam.training.ticketservice.core.repository;

import com.epam.training.ticketservice.core.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, String> {
    Optional<MovieEntity> findByTitle(String title);
    @Transactional
    @Modifying
    @Query("UPDATE MovieEntity me SET me.genre=:genre, me.length=:len WHERE me.title=:title")
    int updateMovie(String title, String genre, int len);
    @Transactional
    @Modifying
    int deleteByTitle(String title);
}
