/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.guessinggame.GuessingGameDao;

import com.app.guessinggame.GuessingGameDto.Rounds;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nur
 */
@Repository
public class RoundsDaoDB implements RoundsDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Rounds> getAllRoundsInGame(int gameId) {
         final String GET_ALL_ROUNDS = "SELECT * FROM Rounds";
        return jdbc.query(GET_ALL_ROUNDS, new RoundMapper());
    }
       
    
    @Override
    public Rounds createRounds(Rounds round) {
        final String INSERT_ROUNDS = "INSERT INTO Rounds(guessNumber, time, results, gameId ) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_ROUNDS,
                round.getGuessNumber(),
                round.getTime(),
                round.getResults(),
                round.getGameID());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundsID(newId);
        return round;
    }

    @Override
    public Rounds getRounds(int roundId) {
        try {
            final String GET_ROUNDS_BY_ID = "SELECT * FROM Rounds WHERE id = ?";
            return jdbc.queryForObject(GET_ROUNDS_BY_ID, new RoundMapper(), roundId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    public static final class RoundMapper implements RowMapper<Rounds> {

        @Override
        public Rounds mapRow(ResultSet rs, int index) throws SQLException {
            Rounds rounds = new Rounds();
            rounds.setRoundsID(rs.getInt("id"));
            rounds.setGuessNumber(rs.getString("guessNumber"));
            rounds.setTime(rs.getTimestamp("time").toLocalDateTime());
            rounds.setResults(rs.getString("results"));
            rounds.setGameID(rs.getInt("gameId"));
            return rounds;
        }
    }
}
