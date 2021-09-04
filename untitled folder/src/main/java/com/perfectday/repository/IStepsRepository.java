package com.perfectday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfectday.entities.Steps;

/**
 * This interface represents the JPA Repository for the steps table of the
 * database.
 */
@Repository
public interface IStepsRepository extends JpaRepository<Steps, Long> {

}
