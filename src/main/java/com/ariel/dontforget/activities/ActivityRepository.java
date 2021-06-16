package com.ariel.dontforget.activities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity,Long> {
    Optional<Activity> findByName(String name);
}
