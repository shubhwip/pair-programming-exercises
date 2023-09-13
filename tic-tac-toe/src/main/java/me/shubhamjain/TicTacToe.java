package me.shubhamjain;

import java.util.Optional;

public interface TicTacToe {
    Optional<GameResult> play(PlayerAction playerAction);
}
