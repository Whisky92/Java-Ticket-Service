package com.epam.training.ticketservice.core.repository;

import com.epam.training.ticketservice.core.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, String> {

    Optional<MovieEntity> findByTitle(String title);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE MovieEntity me SET me.genre = :genre, me.length = :length WHERE me.title = :title")
    int updateMovie(@Param("title") String title, @Param("genre") String genre, @Param("length") int length);

    @Transactional
    @Modifying
    int deleteByTitle(String title);
}
