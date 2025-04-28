package com.tm.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.university.model.University;
import com.tm.university.repository.UniversityRepository;

@Service
public class UniversityService {

	@Autowired
	private UniversityRepository universityRepository;
 

	public List<University> getAllUniversities() {
		return universityRepository.findAll();
	}

	

}
