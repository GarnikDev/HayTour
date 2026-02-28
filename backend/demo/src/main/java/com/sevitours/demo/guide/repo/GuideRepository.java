package com.sevitours.demo.guide.repo;

import com.sevitours.demo.guide.model.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GuideRepository extends JpaRepository<Guide, UUID> {

}
