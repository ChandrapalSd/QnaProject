package dev.chandrapal.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.chandrapal.qna.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
