package com.tm.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tm.game.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game,Long>{

}
