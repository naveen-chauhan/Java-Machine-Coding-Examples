package tictactoe.models;

import java.util.Arrays;

/**
 * @author naveen.chauhan on 14/07/22
 */
public class Player {
	private final String userName;
	private final TicTacToken character;

	public String getUserName() {
		return userName;
	}

	public TicTacToken getCharacter() {
		return character;
	}

	public Player(String userName, TicTacToken character) {
		this.userName = userName;
		this.character = character;
	}

	public static Player initPlayerObject(String playerOne) {
		String[] playerDetails = playerOne.split(" ");
		if (playerDetails.length != 2) {
			return null;
		}

		boolean isValidEnum = Arrays
				.stream(TicTacToken.values())
				.anyMatch((token) ->
						token.name().equals(playerDetails[0]));

		if (!isValidEnum) {
			return null;
		}
		return new Player(playerDetails[1], TicTacToken.valueOf(playerDetails[0]));
	}
}
