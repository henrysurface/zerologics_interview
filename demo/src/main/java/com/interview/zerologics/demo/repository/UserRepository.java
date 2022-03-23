package com.interview.zerologics.demo.repository;

import com.interview.zerologics.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
