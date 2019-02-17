package com.verspaetung.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verspaetung.transport.domain.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}