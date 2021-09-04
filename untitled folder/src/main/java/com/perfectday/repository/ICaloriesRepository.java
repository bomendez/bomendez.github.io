package com.perfectday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfectday.entities.Calories;

/**
 * This interface represents the JPA Repository for the calories table of the
 * database.
 */
@Repository
public interface ICaloriesRepository extends JpaRepository<Calories, Long> {

}
