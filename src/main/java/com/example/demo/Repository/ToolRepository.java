package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Dati.Tool;

public interface ToolRepository extends JpaRepository<Tool,Long>{
	
	Optional<Tool> findByName(String name);
	
	boolean existsByName(String name);
	

}
