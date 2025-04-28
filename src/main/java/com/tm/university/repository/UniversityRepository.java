package com.tm.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tm.university.model.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long>{

}
