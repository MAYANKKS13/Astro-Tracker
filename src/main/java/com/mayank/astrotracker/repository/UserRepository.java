package com.mayank.astrotracker.repository;

import com.mayank.astrotracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
