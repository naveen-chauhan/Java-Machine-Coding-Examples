package trello.models;

import trello.processor.IdGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naveen.chauhan on 12/07/22
 */
public class Lists {
	private String id;
	private final String boardId;
	private String name;
	private final List<Card> cardList;

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBoardId() {
		return boardId;
	}

	private Lists(String name, String boardId) {
		this.name = name;
		this.boardId = boardId;
		this.cardList = new ArrayList<>();
		this.id = IdGenerator.generateId();
	}

	public static Lists createLists(String name, String boardId) {
		return new Lists(name, boardId);
	}

	@Override
	public String toString() {
		return "Lists{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", cardList=" + printCards(cardList) +
				'}';
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	private String printCards(List<Card> cardList) {
		final String[] result = {""};
		cardList.forEach(l -> result[0] = result[0] + l.toString());
		return result[0];
	}
}
