package com.nasa.asteral.model.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "MY_FAVORITES")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteAsteroid {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FAVORITE_ID")
    private Long favoriteId;

	@Column(name = "ASTEROID_REFERENCE_ID")
	private String asteroidReferenceId;
	
	@Column(name = "USERNAME")
	private String username;
	
}
