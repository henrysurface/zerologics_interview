package com.interview.zerologics.demo.repository;

import com.interview.zerologics.demo.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRepository extends JpaRepository<Star, Long> {
}
