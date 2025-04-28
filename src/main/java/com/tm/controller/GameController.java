package com.tm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tm.game.model.Game;
import com.tm.game.model.Publisher;
import com.tm.game.service.GameService;
import com.tm.game.service.PublisherService;

@RestController
@RequestMapping(path = "/api/v1/game/")
public class GameController {

	@Autowired
	private GameService gameService;

	@Autowired
	private PublisherService publisherService;

	@GetMapping(path = "games", name = "Get list of games")
	public List<Game> getAllGames() {
		return gameService.getAllGames();
	}

	@GetMapping(path = "publishers", name = "Get list of all game publishers")
	public List<Publisher> getAllPublishers() {
		return publisherService.getAllPublishers();
	}

}
