package tictactoe.dao;

/**
 * @author naveen.chauhan on 14/07/22
 */
public interface IGameProcessor {
    void process(Integer xCoordinate, Integer yCoordinate);
    void printBoard();
    void printIfPlayerWon();
}
