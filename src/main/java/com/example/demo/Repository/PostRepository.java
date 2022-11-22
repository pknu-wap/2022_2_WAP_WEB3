package com.example.demo.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
	
}
