package com.sevitours.demo.user.repo;

import com.sevitours.demo.user.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,UUID> {

    AppUser findByUsername(String username);

}
