package com.tm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tm.university.model.Country;
import com.tm.university.model.University;
import com.tm.university.service.CountryService;
import com.tm.university.service.UniversityService;

@RestController
@RequestMapping(path = "/api/v1/university/")
public class UniversityController {
	
	@Autowired
	private UniversityService universityService;

	@Autowired
	private CountryService countryService;
	
	@GetMapping(path = "universities", name = "Get list of universities")
	public List<University> getAllUniversities() {
		return universityService.getAllUniversities();
	}

	@GetMapping(path = "counties", name = "Get list of all university countries")
	public List<Country> getAllCountries() {
		return countryService.getAllCountries();
	}
	
}
