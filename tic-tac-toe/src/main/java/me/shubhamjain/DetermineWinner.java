package me.shubhamjain;

import java.util.Optional;

public class DetermineWinner {

    Optional<GameResult> getWinner(char[][] board) {
        if(checkRowsAndColumns(board, 'X') || checkDiagonals(board, 'X'))
            return Optional.of(new GameResult(GameResultType.WIN, 'X'));
        else if(checkRowsAndColumns(board, 'O') || checkDiagonals(board, 'O'))
            return Optional.of(new GameResult(GameResultType.WIN, 'O'));
        else if(allPositionsOccupied(board))
            return Optional.of(new GameResult(GameResultType.TIE, 'N'));
        else
            return Optional.empty();
    }

    private boolean allPositionsOccupied(char[][] board) {
        for(char[] b : board) {
            for(char a : b) {
                if(a == 0)
                    return false;
            }
        }
        return true;
    }

    private boolean checkRowsAndColumns(char[][] board, char playerId) {
        int counter1 = 0;
        int counter2 = 0;
        int rows = board.length;
        int columns = board[0].length;
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                if(board[i][j] == playerId)
                    counter1++;
                if(board[j][i] == playerId)
                    counter2++;
            }
            if(counter1 == rows || counter2 == rows)
                return true;
            counter2 = 0;
            counter1 = 0;
        }
        return false;
    }

    private boolean checkDiagonals(char[][] board, char playerId) {
        int counter1 = 0;
        int counter2 = 0;
        int rows = board.length;
        for(int i=0; i<rows; i++) {
            if(board[i][i] == playerId)
                counter1++;
            if(board[i][rows-i-1] == playerId)
                counter2++;
        }
        return counter1 == rows || counter2 == rows;
    }
}
