package me.shubhamjain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class TicTacToeTest {

    char[][] gameBoard;

    TicTacToe ticTacToe;

    DetermineWinner determineWinner;

    @BeforeEach
    void setUp() {
        gameBoard = new char[3][3];
        determineWinner = new DetermineWinner();
        ticTacToe = new TicTacToeImpl(gameBoard, determineWinner);
    }

    @Test
    void givenTwoPlayersAndValidGame_whenTwoPlayersPlayAndPlayer_O_wins_returnsCorrectWinner() {
        // First chance
        PlayerAction playerAction1_1 = new PlayerAction('X', 1, 1);
        Optional<GameResult> gameResult1 = ticTacToe.play(playerAction1_1);
        Assertions.assertEquals(Optional.empty(), gameResult1);

        PlayerAction playerAction2_1 = new PlayerAction('O', 2, 0);
        Optional<GameResult> gameResult2 = ticTacToe.play(playerAction2_1);
        Assertions.assertEquals(Optional.empty(), gameResult2);

        // Second chance
        PlayerAction playerAction1_2 = new PlayerAction('X', 0, 0);
        Optional<GameResult> gameResult3 = ticTacToe.play(playerAction1_2);
        Assertions.assertEquals(Optional.empty(), gameResult3);

        PlayerAction playerAction2_2 = new PlayerAction('O', 2, 1);
        Optional<GameResult> gameResult4 = ticTacToe.play(playerAction2_2);
        Assertions.assertEquals(Optional.empty(), gameResult4);

        // Third chance
        PlayerAction playerAction1_3 = new PlayerAction('X', 0, 2);
        Optional<GameResult> gameResult5 = ticTacToe.play(playerAction1_3);
        Assertions.assertEquals(Optional.empty(), gameResult5);

        PlayerAction playerAction2_3 = new PlayerAction('O', 2, 2);
        Optional<GameResult> gameResult6 = ticTacToe.play(playerAction2_3);
        Assertions.assertEquals("WIN", gameResult6.get().gameResultType().name());
        Assertions.assertEquals('O', gameResult6.get().playerId());
    }

    @Test
    void givenTwoPlayersAndValidGame_whenTwoPlayersPlayAndPlayerAndThereIsATie_returnsCorrectTie() {
        // First chance
        PlayerAction playerAction1_1 = new PlayerAction('X', 1, 1);
        Optional<GameResult> gameResult1 = ticTacToe.play(playerAction1_1);
        Assertions.assertEquals(Optional.empty(), gameResult1);

        PlayerAction playerAction2_1 = new PlayerAction('O', 1, 0);
        Optional<GameResult> gameResult2 = ticTacToe.play(playerAction2_1);
        Assertions.assertEquals(Optional.empty(), gameResult2);

        // Second chance
        PlayerAction playerAction1_2 = new PlayerAction('X', 2, 0);
        Optional<GameResult> gameResult3 = ticTacToe.play(playerAction1_2);
        Assertions.assertEquals(Optional.empty(), gameResult3);

        PlayerAction playerAction2_2 = new PlayerAction('O', 0, 2);
        Optional<GameResult> gameResult4 = ticTacToe.play(playerAction2_2);
        Assertions.assertEquals(Optional.empty(), gameResult4);

        // Third chance
        PlayerAction playerAction1_3 = new PlayerAction('X', 0, 1);
        Optional<GameResult> gameResult5 = ticTacToe.play(playerAction1_3);
        Assertions.assertEquals(Optional.empty(), gameResult5);

        PlayerAction playerAction2_3 = new PlayerAction('O', 2, 1);
        Optional<GameResult> gameResult6 = ticTacToe.play(playerAction2_3);
        Assertions.assertEquals(Optional.empty(), gameResult6);

        // Third chance
        PlayerAction playerAction1_4 = new PlayerAction('X', 1, 2);
        Optional<GameResult> gameResult7 = ticTacToe.play(playerAction1_4);
        Assertions.assertEquals(Optional.empty(), gameResult7);

        PlayerAction playerAction2_4 = new PlayerAction('O', 2, 2);
        Optional<GameResult> gameResult8 = ticTacToe.play(playerAction2_4);
        Assertions.assertEquals(Optional.empty(), gameResult8);

        // Third chance
        PlayerAction playerAction1_5 = new PlayerAction('X', 0, 0);
        Optional<GameResult> gameResult9 = ticTacToe.play(playerAction1_5);
        Assertions.assertEquals(GameResultType.TIE, gameResult9.get().gameResultType());

    }
}
