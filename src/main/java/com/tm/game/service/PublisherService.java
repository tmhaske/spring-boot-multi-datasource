package com.tm.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.game.model.Publisher;
import com.tm.game.repository.PublisherRepository;

@Service
public class PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;

	public List<Publisher> getAllPublishers() {
		return publisherRepository.findAll();
	}

}
