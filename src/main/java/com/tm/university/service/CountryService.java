package com.tm.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.university.model.Country;
import com.tm.university.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}

}
