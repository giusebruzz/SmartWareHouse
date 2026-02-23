package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Dati.LastSeen;

public interface LastSeenRepository extends JpaRepository<LastSeen,Long>{
	


}
