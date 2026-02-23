package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Dati.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
	
	Optional<Role>findByRole(String role);
	

}
