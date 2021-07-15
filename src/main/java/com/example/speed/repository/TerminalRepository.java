package com.example.speed.repository;

import com.example.speed.entity.TerminalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalRepository extends JpaRepository<TerminalEntity, Long> {
}