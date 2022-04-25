/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.guessinggame.GuessingGameDao;

import com.app.guessinggame.GuessingGameDto.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nur
 */
@Repository
public class GameDaoDB implements GameDao {

     @Autowired
    JdbcTemplate jdbc;

    @Override
    public Game getGameById(int id) {
        try {
            final String GET_GAME_BY_ID = "SELECT * FROM game WHERE id = ?";
            return jdbc.queryForObject(GET_GAME_BY_ID, new GameMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Game> getAllGames() {
        final String GET_ALL_GAMES = "SELECT * FROM Game";
        return jdbc.query(GET_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game createGame(Game game) {
        final String INSERT_GAME = "INSERT INTO game(randomNum, status) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_GAME,
                game.getRandomNum(),
                game.getGameStatus()
                );

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameID(newId);
        return game;
    }

    @Override
    public void updateGame(Game game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameID(rs.getInt("id"));
            game.setRandomNum(rs.getString("randomNum"));
            game.setGameStatus(rs.getBoolean("status"));

            return game;
        }
    }
}
