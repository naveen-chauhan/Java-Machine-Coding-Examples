package tictactoe;

import tictactoe.dao.GameProcessor;
import tictactoe.dao.IGameProcessor;
import tictactoe.models.Player;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author naveen.chauhan on 14/07/22
 */
public class TicTocToeMainDriver {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String playerDetails = scanner.nextLine();

		Player playerOne = Player.initPlayerObject(playerDetails);
		while (Objects.isNull(playerOne)) {
			System.out.println("Error: Invalid PlayerOne Entry, Please Try Again");
			playerOne = Player.initPlayerObject(scanner.nextLine());
		}

		playerDetails = scanner.nextLine();
		Player playerTwo = Player.initPlayerObject(playerDetails);
		while (Objects.isNull(playerTwo)) {
			System.out.println("Error: Invalid PlayerTwo Entry, Please Try Again");
			playerTwo = Player.initPlayerObject(scanner.nextLine());
		}

		IGameProcessor gameProcessor = new GameProcessor(playerOne, playerTwo);
		String input = "";
		input = scanner.nextLine();
		while (!input.equalsIgnoreCase("Exit")) {
			String[] inputDetails = input.split(" ");
			Integer firstCoordinate  = Integer.valueOf(inputDetails[0].toString());
			Integer secondCoordinate  = Integer.valueOf(inputDetails[1].toString());

			gameProcessor.process(firstCoordinate, secondCoordinate);

			gameProcessor.printIfPlayerWon();
			input = scanner.nextLine();
		}
	}
}
