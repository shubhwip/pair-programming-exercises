package me.shubhamjain;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public class TicTacToeImpl implements TicTacToe {
    char[][] board;
    DetermineWinner determineWinner;
    @Override
    public Optional<GameResult> play(PlayerAction playerAction) {
        board[playerAction.i()][playerAction.j()] = playerAction.playerId();
        Arrays.stream(board).forEach(System.out::println);
        return determineWinner.getWinner(board);
    }
}
