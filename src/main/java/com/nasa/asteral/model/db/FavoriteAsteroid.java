package com.nasa.asteral.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
