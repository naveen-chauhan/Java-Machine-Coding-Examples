package snakeandladder;

import snakeandladder.models.PlayerInfo;
import snakeandladder.processor.GameProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author naveen.chauhan on 01/07/22
 */
public class SnakeAndLadderMainDriver {
	public static void main(String[] args) {
		int numberOfSnake;
		int numberOfLadder;
		Scanner scanner = new Scanner(System.in);
		SnakeAndLadderGameBoard gameBoard = new SnakeAndLadderGameBoard();

		numberOfSnake = scanner.nextInt();

		while (numberOfSnake-- > 0) {
			int startPosition;
			int endPosition;
			startPosition = scanner.nextInt();
			endPosition = scanner.nextInt();

			gameBoard.addSnake(startPosition, endPosition);
		}

		numberOfLadder = scanner.nextInt();

		while (numberOfLadder-- > 0) {
			int startPosition;
			int endPosition;
			startPosition = scanner.nextInt();
			endPosition = scanner.nextInt();

			gameBoard.addLadder(startPosition, endPosition);
		}

		int numberOfPlayer;


		numberOfPlayer = scanner.nextInt();
		List<PlayerInfo> players = new ArrayList<>();

		//This is an issue. https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
		scanner.nextLine();

		for (int i = 0; i < numberOfPlayer; i++) {
			String nameOfPlayer = scanner.nextLine();
			System.out.println("Current Input: " + nameOfPlayer);
			players.add(new PlayerInfo(nameOfPlayer));
		}

		GameProcessor gameProcessor = new GameProcessor();
		gameProcessor.executeGame(gameBoard, players);

	}
}
