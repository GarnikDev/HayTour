package com.sevitours.demo.stop.repo;

import com.sevitours.demo.stop.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StopRepo extends JpaRepository<Stop, UUID> {
}
