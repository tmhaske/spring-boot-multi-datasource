package com.tm.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.game.model.Game;
import com.tm.game.repository.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;

	public List<Game> getAllGames(){
		return gameRepository.findAll();
	}
	
}
