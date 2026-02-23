package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Dati.Work;

public interface WorkRepository extends JpaRepository<Work,Long> {

	boolean existsByName(String name);
	
	Optional<Work>findByName(String name);
	

}
