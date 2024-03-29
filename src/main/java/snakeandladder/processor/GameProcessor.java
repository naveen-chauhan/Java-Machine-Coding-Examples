package snakeandladder.processor;

import snakeandladder.SnakeAndLadderGameBoard;
import snakeandladder.models.PlayerInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * @author naveen.chauhan on 01/07/22
 */
public class GameProcessor {
	private static final Random random = new Random();

	public int getRandomNumber() {
		return random.nextInt(6) + 1;
	}


	public void executeGame(SnakeAndLadderGameBoard gameBoard, List<PlayerInfo> players) {

		Queue<PlayerInfo> queueOfPlayer = new LinkedList<>(players);

		PlayerInfo currentPlayer = null;

		//Here Starts the game
		while (!isGameEnded(players)) {
			currentPlayer = queueOfPlayer.poll();

			int random_integer = getRandomNumber();


			if (currentPlayer.getCurrentPosition() + random_integer > 100) {
				System.out.println(
						currentPlayer.getName()
								+ " rolled a "
								+ random_integer
								+ " and moved from "
								+ currentPlayer.getCurrentPosition()
								+ " "
								+ currentPlayer.getCurrentPosition());

				currentPlayer.setCurrentPosition(currentPlayer.getCurrentPosition());
				queueOfPlayer.add(currentPlayer);
				continue;
			}

			int finalPositionAfterDiceRolling = gameBoard.getEndPosition(
					gameBoard.isItemPresent(currentPlayer.getCurrentPosition() + random_integer),
					currentPlayer.getCurrentPosition() + random_integer);

			System.out.println(
					currentPlayer.getName()
							+ " rolled a "
							+ random_integer
							+ " and moved from "
							+ currentPlayer.getCurrentPosition()
							+ " "
							+ finalPositionAfterDiceRolling);

			currentPlayer.setCurrentPosition(finalPositionAfterDiceRolling);

			//finally, adding the player back
			queueOfPlayer.add(currentPlayer);
		}

		System.out.println(currentPlayer.getName() + " wins the game");
	}

	private boolean isGameEnded(List<PlayerInfo> players) {
		for (PlayerInfo playerInfo : players) {
			if (playerInfo.isPlayerWon())
				return true;
		}
		return false;
	}
}
