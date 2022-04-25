/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.guessinggame.ServiceLayer;

import com.app.guessinggame.GuessingGameDao.GameDao;
import com.app.guessinggame.GuessingGameDao.RoundsDao;
import com.app.guessinggame.GuessingGameDto.Game;
import com.app.guessinggame.GuessingGameDto.Rounds;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nur
 */
@Service
public class ServiceLayerImpl implements ServiceLayer {
    
    @Autowired
    GameDao gameDao;
    @Autowired
    RoundsDao roundsDao;

   // public ServiceLayerImpl(GameDao gameDao, RoundsDao roundsDao) {
       // this.gameDao = gameDao;
        //this.roundsDao = roundsDao;
    //}
    public List<Rounds> getAllRoundsInGame(int gameId) {
        return roundsDao.getAllRoundsInGame(gameId);
    }

    //public Rounds createRounds(Rounds round) {
       // return roundsDao.createRounds(round);
    //}

    
    public Rounds getRounds(int roundId) {
      return roundsDao.getRounds(roundId);
    }

    
    public Game getGameById(int id) {
       return gameDao.getGameById(id);
    }

    
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    
    public Game createGame() {
        Random generator = new Random();
       Game createdGame = new Game();
       
       int[] randomNum = new int[4];
        for (int i = 0; i < randomNum.length; i++) {
            boolean valid = false;
            while (valid == false) {
                int a = generator.nextInt(8) + 1;
                for (int b = 0; b < randomNum.length; b++) {
                    if (i == b) {
                        continue;
                    }
                    if (a > randomNum[b] || a < randomNum[b]) {
                        valid = true;
                    } else {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    randomNum[i] = a;
                }
            }
        }
        createdGame.setRandomNum("" + randomNum[0] + randomNum[1] + randomNum[2] + randomNum[3]);
        createdGame.setGameStatus(false);

        return gameDao.createGame(createdGame);
    }

    public String GameResults(int gameId, String guessNumber) {
        int partialAnswer = 0;
        int exactAnswer = 0;
        Game newGame = gameDao.getGameById(gameId);
        String random = newGame.getRandomNum();
        for (int i = 0; i < random.length(); i++) {
            char c = random.charAt(i);
            char b = guessNumber.charAt(i);
            if (c == b) {
                exactAnswer = exactAnswer + 1;
            } else if (random.indexOf(b) != i && random.indexOf(b) != -1) {
                partialAnswer = partialAnswer + 1;
            }
        }
        String result = ( "e:"+exactAnswer + "p:" + partialAnswer);
      return result;
    }

    @Override
    public Rounds createRounds(Rounds round) {
      
      
       
        round.setResults(GameResults(round.getGameID(),round.getGuessNumber()));
        return roundsDao.createRounds(round);
    }
    private void updateStatus(Rounds round){
       Game game =  gameDao.getGameById(round.getRoundsID());
       if(round.getResults().equalsIgnoreCase("e:4:p:0")){
          game.setGameStatus(true);
       }
       
    }

//    @Override
//    public Rounds getGameResults(int gameId, String guessNumber) {
//        
//    }

    public Rounds getGameResults(int gameId, String guessNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
    

