/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.guessinggame.GuessingGameDao;

import com.app.guessinggame.GuessingGameDto.Game;
import java.util.List;

/**
 *
 * @author Nur
 */
public interface GameDao {
     public Game getGameById(int id);
    
    public List<Game> getAllGames();
    public void updateGame(Game game);
    public Game createGame (Game game);
}
