package com.interview.zerologics.demo.repository;

import com.interview.zerologics.demo.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository<Text, Long> {
}
