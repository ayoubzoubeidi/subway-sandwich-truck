package com.example.subwaysandwichtruck.repository;

import com.example.subwaysandwichtruck.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
}
