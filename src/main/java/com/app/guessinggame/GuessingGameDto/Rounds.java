/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.guessinggame.GuessingGameDto;

import java.time.LocalDateTime;

/**
 *
 * @author Nur
 */
public class Rounds {
    private int roundsID;
    private int gameID;
    private String guessNumber;
    private LocalDateTime time = LocalDateTime.now();
    private String results;

    public int getRoundsID() {
        return roundsID;
    }

    public void setRoundsID(int roundsID) {
        this.roundsID = roundsID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(String guessNumber) {
        this.guessNumber = guessNumber;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
    
}
