package trello.processor;

import trello.models.Board;
import trello.models.Card;
import trello.models.Lists;

import java.util.Map;

/**
 * @author naveen.chauhan on 12/07/22
 */
public class ShowSvc {
	public void show(Map<String, Board> boardMap) {
		if (boardMap.isEmpty()) {
			System.out.println("No Boards");
			return;
		}
		final String[] result = {""};
		boardMap.values().forEach(board -> {
			result[0] = result[0] + board.toString();
		});
		System.out.println("[ " + result[0] + " ]");
	}

	public void showBoard(Board board) {
		System.out.println(board.toString());
	}

	public void showList(Lists lists) {
		System.out.println(lists.toString());
	}

	public void showCard(Card card) {
		System.out.println(card.toString());
	}
}
