package com.perfectday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfectday.entities.Distance;

/**
 * This interface represents the JPA Repository for the distance table of the
 * database.
 */
@Repository
public interface IDistanceRepository extends JpaRepository<Distance, Long> {

}
