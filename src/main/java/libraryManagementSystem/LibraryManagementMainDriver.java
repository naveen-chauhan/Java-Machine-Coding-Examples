package libraryManagementSystem;

import libraryManagementSystem.dao.LibraryManager;

import java.util.Scanner;

/**
 * @author naveen.chauhan on 17/07/22
 */
public class LibraryManagementMainDriver {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		input = scanner.nextLine();
		String[] commands = input.split(" ");
		LibraryManager libraryManager = new LibraryManager(commands);
		while (!input.equalsIgnoreCase("Exit")) {
			input = scanner.nextLine();
			libraryManager.execute(input.split(" "));
		}
	}
}
