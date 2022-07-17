package chessvalidator;

import chessvalidator.service.ChessSvc;

import java.util.Scanner;

/**
 * @author naveen.chauhan on 16/07/22
 */
public class ChessValidatorMainClass {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);


		ChessSvc chessSvc = ChessSvc.initiateBoard();
		chessSvc.printBoard();

		String input = scanner.nextLine();
		String currentPlayer = "W";
		while (!input.equalsIgnoreCase("Exit")) {
			String[] commands = input.split(" ");
			chessSvc.movePiece(commands, currentPlayer);
			currentPlayer = "W".equals(currentPlayer) ? "B":"W";
			input = scanner.nextLine();
		}
	}
}
