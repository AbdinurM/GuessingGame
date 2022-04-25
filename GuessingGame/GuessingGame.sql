drop database if exists GuessingGame;

create database GuessingGame;

use GuessingGame;


create table Game(
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
randomNum VARCHAR(45) NOT NULL,
`status` BIT NOT NULL);


create table Rounds(
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
guessNumber VARCHAR(45) NOT NULL,
`time` DATETIME NOT NULL,
results VARCHAR(45) NOT NULL,
gameId INT NOT NULL,
 FOREIGN KEY (gameId) REFERENCES Game(id));