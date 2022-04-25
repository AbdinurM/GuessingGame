/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.guessinggame.Controller;

import com.app.guessinggame.GuessingGameDto.Game;
import com.app.guessinggame.GuessingGameDto.Rounds;
import com.app.guessinggame.ServiceLayer.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nur
 */
@RestController
@RequestMapping("/api")
public class Controller {
   
    @Autowired
    ServiceLayer serv;
    //public Controller(ServiceLayer serv) {
        //this.serv = serv;
    //}
    
   @PostMapping("/StartAGame")
   @ResponseStatus(HttpStatus.CREATED)
   public int startGame(){
       return serv.createGame().getGameID();
   }
   
   @PostMapping("/UserGuess")
   public String userGuess(@RequestBody Rounds rounds ){
       return serv.GameResults(rounds.getGameID(), rounds.getGuessNumber());
   }
   
   @GetMapping("/AllGames")
   public List<Game> allGame(){
       return serv.getAllGames();
   }
   
   @GetMapping("/GetAGame/{gameID}")
   public Game getAGame(@PathVariable int gameID){
      return serv.getGameById(gameID);
       
   }
   
   @GetMapping("/GetRounds/{gameID}")
   public List<Rounds> getRounds(@PathVariable int gameID){
       return serv.getAllRoundsInGame(gameID);
   }
}
