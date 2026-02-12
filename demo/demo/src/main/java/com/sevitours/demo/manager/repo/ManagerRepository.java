package com.sevitours.demo.manager.repo;

import com.sevitours.demo.manager.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
}
