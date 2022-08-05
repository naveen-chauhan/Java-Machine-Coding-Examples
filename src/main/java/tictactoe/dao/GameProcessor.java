package tictactoe.dao;

import tictactoe.models.Player;

import java.util.Arrays;

import static tictactoe.models.TicTacToken.X;

/**
 * @author naveen.chauhan on 14/07/22
 */
public class GameProcessor implements IGameProcessor {

	private Player playerOne;
	private Player playerTwo;
	private Player currentPlayer;
	private Character[][] ticTacBoard;

	public GameProcessor(Player playerOne, Player playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.ticTacBoard = new Character[4][4];
		Arrays.stream(ticTacBoard).forEach(ticTac -> Arrays.fill(ticTac, '-'));
		if (playerOne.getCharacter().equals(X)) {
			this.currentPlayer = playerOne;
		} else {
			this.currentPlayer = playerTwo;
		}
	}

	@Override
	public void process(Integer xCoordinate, Integer yCoordinate) {
		if (isAnyPlayerWon()) {
			return;
		}
		if (ticTacBoard[xCoordinate][yCoordinate] != '-') {
			System.out.println("Invalid Move");
			return;
		}
		ticTacBoard[xCoordinate][yCoordinate] = currentPlayer.getCharacter().name().charAt(0);
		currentPlayer = (currentPlayer.equals(playerOne))? playerTwo:playerOne;
		printBoard();
	}

	private boolean isAnyPlayerWon() {
		for (int i = 1; i<ticTacBoard.length ; i++) {
			if (ticTacBoard[i][1].equals(ticTacBoard[i][2]) && ticTacBoard[i][2].equals(ticTacBoard[i][3]) && isValidLiteral(ticTacBoard[i][1])){
				return true;
			}
		}

		for (int j = 1; j<ticTacBoard.length ; j++) {
			if (isValidLiteral(ticTacBoard[1][j]) && ticTacBoard[1][j].equals(ticTacBoard[2][j]) && ticTacBoard[2][j].equals(ticTacBoard[3][j])){
				return true;
			}
		}

		if (isValidLiteral(ticTacBoard[1][1]) && ticTacBoard[1][1].equals(ticTacBoard[2][2]) && ticTacBoard[2][2].equals(ticTacBoard[3][3])) {
			return true;
		}

		if (isValidLiteral(ticTacBoard[2][2]) && ticTacBoard[1][3].equals(ticTacBoard[2][2]) && ticTacBoard[2][2].equals(ticTacBoard[3][1])) {
			return true;
		}

		return false;


	}

	private boolean isValidLiteral(Character character) {
		return (character.equals('X') || character.equals('O'));
	}

	@Override
	public void printBoard() {
		for (int i = 1; i < ticTacBoard.length; i++) {
			for (int j = 1; j < ticTacBoard[0].length; j++) {
				System.out.print(ticTacBoard[i][j]+ " ");
			}
			System.out.println();
		}
	}

	@Override
	public void printIfPlayerWon() {
		if (isAnyPlayerWon()) {
			Player wonPlayer =  (currentPlayer.equals(playerOne))? playerTwo:playerOne;
			System.out.println(wonPlayer.getUserName() + " won the game");
		}
 	}
}
