package libraryManagementSystem.model;

import libraryManagementSystem.model.BookCopy;

import java.util.HashMap;

/**
 * @author naveen.chauhan on 17/07/22
 */
public class Library {
	private BookCopy[] rack;
	private HashMap<String, LibraryUser> libraryUserMap;

	public Library(String command) {
		int rackSize = Integer.parseInt(command);
		this.rack = new BookCopy[rackSize];
		libraryUserMap = new HashMap<>();
	}

	public BookCopy[] getRack() {
		return rack;
	}

	public HashMap<String, LibraryUser> getLibraryUserMap() {
		return libraryUserMap;
	}
}
