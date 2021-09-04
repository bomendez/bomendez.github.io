package com.perfectday.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.perfectday.entities.Activity;

/**
 * This interface represents the JPA Repository for the activity table of the
 * database.
 */

@Repository
public interface IActivityRepository extends JpaRepository<Activity, Long> {

	@Query("SELECT a from Activity a where a.activity_type =:activity_type ")
	Optional<List<Activity>> findByActivityType(@Param("activity_type") String activity_type);

	@Query("SELECT a from Activity a where a.activity_type =:activity_type and a.activity_id =:activity_id")
	Optional<Activity> findByActivityTypeAndId(@Param("activity_type") String activity_type,
			@Param("activity_id") Long id);

	@Query("SELECT COUNT(a) from Activity a where a.activity_type =:activity_type")
	Integer countByActivityType(@Param("activity_type") String activity_type);

}
