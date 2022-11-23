package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.ImageEntity;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, String> {
	
}