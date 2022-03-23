package com.interview.zerologics.demo.repository;

import com.interview.zerologics.demo.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
