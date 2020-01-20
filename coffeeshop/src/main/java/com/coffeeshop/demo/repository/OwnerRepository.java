package com.coffeeshop.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffeeshop.demo.message.request.OwnerDashboard;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerDashboard, Long> {
    
	Optional<OwnerDashboard> findByOwnerUserName(String ownerUserName);
}