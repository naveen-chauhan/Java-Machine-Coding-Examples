package boardgame2048;

import boardgame2048.service.BoardGameSvc;

import java.util.Scanner;

/**
 * @author naveen.chauhan on 18/07/22
 */
public class Game2048MainDriver {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		BoardGameSvc boardGameSvc = BoardGameSvc.init();
		boardGameSvc.showBoard();
		while (!input.equalsIgnoreCase("Exit")) {
			input = scanner.nextLine();
			switch (input) {
				case "0":
					boardGameSvc.moveLeft();
					boardGameSvc.showBoard();
					break;
				case "1":
					boardGameSvc.moveRight();
					boardGameSvc.showBoard();
					break;
				case "2":
					boardGameSvc.moveTop();
					boardGameSvc.showBoard();
					break;
				case "3":
					boardGameSvc.moveBottom();
					boardGameSvc.showBoard();
					break;
			}

			if (boardGameSvc.isPlayerWon()) {
				System.out.println("Congratulations");
			}

			if (!boardGameSvc.isPossibleToAddTiles(boardGameSvc.getBoard()) && !boardGameSvc.isMergingPossible()) {
				System.out.println("Game over");
			}
		}
	}
}
