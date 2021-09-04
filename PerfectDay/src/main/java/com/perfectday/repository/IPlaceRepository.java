package com.perfectday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfectday.entities.Place;

/**
 * This interface represents the JPA Repository for the place table of the
 * database.
 */
@Repository
public interface IPlaceRepository extends JpaRepository<Place, Long> {

}
