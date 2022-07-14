package trello;

import trello.models.Board;
import trello.models.Card;
import trello.models.Lists;
import trello.models.PRIVACY;

import java.util.HashMap;
import java.util.Map;

/**
 * @author naveen.chauhan on 12/07/22
 */
public class TrelloProcessor {
	Map<String, Board> boardMap;
	Map<String, Card> cardMap;
	Map<String, Lists> listsMap;
	private ShowSvc showSvc = new ShowSvc();

	public TrelloProcessor() {
		boardMap = new HashMap<>();
		cardMap = new HashMap<>();
		listsMap = new HashMap<>();
	}

	public void show(String[] arguments) {
		if (arguments.length == 1) {
			showSvc.show(boardMap);
		} else if (arguments[1].equals("BOARD")) {
			if (isInvalidBoardId(arguments[2])) {
				return;
			}
			showSvc.showBoard(boardMap.get(arguments[2]));
		} else if (arguments[1].equals("LIST")) {
			if (isInvalidListId(arguments[2])) {
				return;
			}
			showSvc.showList(listsMap.get(arguments[2]));
		} else if (arguments[1].equals("CARD")) {
			if (isInvalidCardId(arguments[2])) {
				return;
			}
			showSvc.showCard(cardMap.get(arguments[2]));
		} else {
			System.out.println("Invalid Show data !!");
		}
	}

	public void processBoard(String[] arguments) {
		if (arguments[1].equals("CREATE")) {
			Board board = Board.create(arguments[2]);
			boardMap.put(board.getId(), board);
			System.out.println("Created board: " + board.getId());
		} else if (arguments[2].equals("name")) {
			if (isInvalidBoardId(arguments[1])) return;
			boardMap.get(arguments[1]).setName(arguments[3]);
		} else if (arguments[2].equals("privacy")) {
			if (isInvalidBoardId(arguments[1])) return;
			//Todo: Check if the argument is valid Enum Value. Else it will fail
			boardMap.get(arguments[1]).setPrivacy(PRIVACY.valueOf(arguments[3]));
		} else if (arguments[2].equals("ADD_MEMBER")) {
			if (isInvalidBoardId(arguments[1])) return;
			boardMap.get(arguments[1]).addMember(arguments[3]);
		} else if (arguments[2].equals("REMOVE_MEMBER")) {
			if (isInvalidBoardId(arguments[1])) return;
			boardMap.get(arguments[1]).removeMember(arguments[3]);
		} else if (arguments[1].equals("DELETE")) {
			if (isInvalidBoardId(arguments[2])) {
				return;
			}
			Board board = boardMap.get(arguments[2]);
			boardMap.remove(arguments[2]);
		} else {
			System.out.println("Invalid Board Command !!");
		}
	}

	public void processList(String[] arguments) {
		if (arguments[1].equals("CREATE")) {
			if (isInvalidBoardId(arguments[2])) {
				System.out.println("Board " + arguments[2] + " does not exist");
				return;
			}
			Lists lists = Lists.createLists(arguments[3], arguments[2]);
			listsMap.put(lists.getId(), lists);
			boardMap.get(arguments[2]).getListsList().add(lists);
			System.out.println("Created list: " + lists.getId());
		} else if (arguments[2].equals("name")) {
			if (isInvalidListId(arguments[1])) {
				return;
			}
			Lists lists = listsMap.get(arguments[1]);
			lists.setName(arguments[3]);
		} else if (arguments[1].equals("DELETE")) {
			if (isInvalidListId(arguments[2])) {
				return;
			}
			Lists lists = listsMap.get(arguments[2]);
			listsMap.remove(arguments[2]);

			//Todo: if we wont be given the boardId, how to delete from the boardMap
			//Here is the implementation for removing the reference
			boardMap.get(lists.getBoardId()).getListsList().remove(lists);

		} else {
			System.out.println("Invalid Lists Command !!");
		}
	}

	public void processCards(String[] arguments) {
		if (arguments[1].equals("CREATE")) {
			if (isInvalidListId(arguments[2])) {
				return;
			}
			Card card = Card.create(arguments[3], arguments[2]);
			cardMap.put(card.getId(), card);
			listsMap.get(arguments[2]).getCardList().add(card);
			System.out.println("Created card: " + card.getId());
		} else if (arguments[2].equals("name")) {
			if (isInvalidCardId(arguments[1])) {
				return;
			}
			cardMap.get(arguments[1]).setName(arguments[3]);
		} else if (arguments[2].equals("description")) {
			if (isInvalidCardId(arguments[1])) {
				return;
			}
			cardMap.get(arguments[1]).setDescription(arguments[3]);
		} else if (arguments[2].equals("ASSIGN")) {
			if (isInvalidCardId(arguments[1])) {
				return;
			}
			cardMap.get(arguments[1]).setAssignedUser(arguments[3]);
		} else if (arguments[2].equals("UNASSIGN")) {
			if (isInvalidCardId(arguments[1])) {
				return;
			}
			cardMap.get(arguments[1]).removeAssigment();
		} else if (arguments[2].equals("MOVE")) {
			if (isInvalidCardId(arguments[1])) {
				return;
			}
			Card card = cardMap.get(arguments[1]);
			//Todo: how to remove from the previous map, as we dont have previous map data
			//Here is the deletion for the card from the previous list
			listsMap.get(card.getListId()).getCardList().remove(card);

			//Set the new ListId
			card.setListId(arguments[3]);

			//Reference back in the listMap
			listsMap.get(arguments[3]).getCardList().add(card);

			listsMap.get(arguments[3]).getCardList().add(card);
		} else if (arguments[1].equals("DELETE")) {
			if (isInvalidCardId(arguments[2])) {
				return;
			}
			//Todo: how to remove from the previous map, as we dont have previous map data
			Card card = cardMap.get(arguments[2]);
			cardMap.remove(arguments[2]);

			listsMap.get(card.getListId()).getCardList().remove(card);
		} else {
			System.out.println("Invalid Card Command !!");
		}
	}

	private boolean isInvalidBoardId(String arguments) {
		if (!boardMap.containsKey(arguments)) {
			System.out.println("No Board with this " + arguments + " present");
			return true;
		}
		return false;
	}

	private boolean isInvalidListId(String listId) {
		if (!listsMap.containsKey(listId)) {
			System.out.println("No List with this " + listId + " present");
			return true;
		}
		return false;
	}

	private boolean isInvalidCardId(String cardId) {
		if (!cardMap.containsKey(cardId)) {
			System.out.println("No Card with this " + cardId + " present");
			return true;
		}
		return false;
	}
}
