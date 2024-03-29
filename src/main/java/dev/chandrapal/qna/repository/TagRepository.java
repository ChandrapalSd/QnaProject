package dev.chandrapal.qna.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.chandrapal.qna.entities.Tag;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String name);

    Page<Tag> findByName(String name, Pageable pageable);

    Page<Tag> findAll(Pageable pageable);

    @Query("SELECT t FROM Tag t ORDER BY SIZE(t.questions) DESC")
    Page<Tag> findAllSortByMostPopular(Pageable pageable);

}
