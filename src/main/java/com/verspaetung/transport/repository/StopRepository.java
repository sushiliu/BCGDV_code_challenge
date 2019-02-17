package com.verspaetung.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verspaetung.transport.domain.Stop;

public interface StopRepository extends JpaRepository<Stop, Integer> {
}