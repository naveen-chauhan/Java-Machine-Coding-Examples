package trello;

import trello.processor.TrelloProcessor;

import java.util.Scanner;

/**
 * @author naveen.chauhan on 12/07/22
 * TotalTime Taken: More than 3 hours
 */
public class TrelloMainService {
	public static void main(String[] args) {
		TrelloProcessor trelloSVC = new TrelloProcessor();
		Scanner scanner = new Scanner(System.in);
		String input = "";
		while (!input.equalsIgnoreCase("exit")) {
			input = scanner.nextLine();
			String[] arguments =  input.split(" ", 4);

			switch (arguments[0]) {
				case "SHOW":
					trelloSVC.show(arguments);
					break;
				case "BOARD":
					trelloSVC.processBoard(arguments);
					break;
				case "LIST":
					trelloSVC.processList(arguments);
					break;
				case "CARD":
					trelloSVC.processCards(arguments);
					break;
				default:
					System.out.println("Error: Invalid Input, Please try again");
					break;
			}
		}
	}
}
