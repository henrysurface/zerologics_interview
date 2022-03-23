package com.interview.zerologics.demo.repository;

import com.interview.zerologics.demo.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share, Long> {
}
