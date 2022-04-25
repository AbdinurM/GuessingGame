/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.guessinggame.GuessingGameDao;

import com.app.guessinggame.GuessingGameDto.Rounds;
import java.util.List;

/**
 *
 * @author Nur
 */
public interface RoundsDao {
   public List<Rounds> getAllRoundsInGame(int gameId);
    
   public Rounds createRounds (Rounds round);
    
   public Rounds getRounds (int roundId);
}
