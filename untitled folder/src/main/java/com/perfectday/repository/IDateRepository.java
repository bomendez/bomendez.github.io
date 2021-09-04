package com.perfectday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfectday.entities.Dates;

/**
 * This interface represents the JPA Repository for the dates table of the
 * database.
 */
@Repository
public interface IDateRepository extends JpaRepository<Dates, Long> {

}
