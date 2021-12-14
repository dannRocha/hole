package com.hole.repositories;

import com.hole.entities.RegistroBuraco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroBuracoRepository extends JpaRepository<RegistroBuraco, Long> { 
}
