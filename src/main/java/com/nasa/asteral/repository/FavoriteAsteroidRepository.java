package com.nasa.asteral.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasa.asteral.model.db.FavoriteAsteroid;

public interface FavoriteAsteroidRepository extends JpaRepository<FavoriteAsteroid, Long> {
	
	Optional<FavoriteAsteroid> findByAsteroidReferenceIdAndUsername(String asteroidReferenceId, String username);

	List<FavoriteAsteroid> findByUsername(String username);
	
	long countByUsername(String username);
	
}
