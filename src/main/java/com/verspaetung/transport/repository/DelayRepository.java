package com.verspaetung.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verspaetung.transport.domain.Delay;

public interface DelayRepository extends JpaRepository<Delay, Integer> {
}