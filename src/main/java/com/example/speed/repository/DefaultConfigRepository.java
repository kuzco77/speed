package com.example.speed.repository;

import com.example.speed.entity.DefaultConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefaultConfigRepository extends JpaRepository<DefaultConfigEntity, Long> {
}