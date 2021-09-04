package com.perfectday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfectday.entities.ActivityTypes;

/**
 * This interface represents the JPA Repository for the activity_types table of
 * the database.
 */
@Repository
public interface IActivityTypesRepository extends JpaRepository<ActivityTypes, String> {

}
