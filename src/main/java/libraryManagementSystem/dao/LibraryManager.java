package libraryManagementSystem.dao;

import libraryManagementSystem.model.BookCopy;
import libraryManagementSystem.model.Library;
import libraryManagementSystem.model.LibraryUser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author naveen.chauhan on 17/07/22
 */
public class LibraryManager {
	private Library library;
	private Map<String, LibraryUser> bookToUserMap;
	private Map<String, LibraryUser> userIdToUserData;
	private UserManagementDepartment userManagementDepartment;
	private LibraryBookStoreDepartment libraryBookStoreDepartment;


	public LibraryManager(String[] commands) {
		this.library = new Library(commands[1]);
		this.libraryBookStoreDepartment = new LibraryBookStoreDepartment();
		this.userManagementDepartment = new UserManagementDepartment();
		this.bookToUserMap = new HashMap<>();
		this.userIdToUserData = new HashMap<>();
		System.out.println("Created library with "+ commands[1] + " racks");
	}


	public void execute(String[] commands) {
		switch (commands[0]) {
			case "borrow_book":
				dispenseBookToUser(commands);
				break;
			case "return_book_copy":
				takeReturnOfCopy(commands);
				break;
			case "add_book":
				libraryBookStoreDepartment.addBookToShelf(commands, library);
				break;
			case "search":
				libraryBookStoreDepartment.searchBookInLibrary(commands, library);
				break;
			case "remove_book_copy":
				libraryBookStoreDepartment.removeBookFromLibrary(commands[1], library);
				break;
			case "print_borrowed":
				userManagementDepartment.showBorrowedBookByUser(commands, library);
		}
	}

	private void takeReturnOfCopy(String[] commands) {
		String bookCopyId = commands[1];
		LibraryUser libraryUser = bookToUserMap.get(bookCopyId);
		BookCopy bookCopy = libraryUser.getBookedCopyToDueDateMap().get(bookCopyId).getBookCopy();
		libraryBookStoreDepartment.addBookToShelf(bookCopy, library);
		bookToUserMap.remove(bookCopyId);
		libraryUser.getBookedCopyToDueDateMap().remove(bookCopyId);
	}

	private void dispenseBookToUser(String[] commands) {
		BookCopy bookCopy = null;
		int j = 0;
		for (j = 0; j < library.getRack().length; j++) {

			if (library.getRack()[j] != null && library.getRack()[j].getBookId().equals(commands[1])) {
				bookCopy = library.getRack()[j];
			}
		}

		LibraryUser libraryUser;
		if (!userIdToUserData.containsKey(commands[2])) {
			libraryUser = new LibraryUser(commands[1]);
		} else {
			libraryUser = userIdToUserData.get(commands[2]);
		}

		libraryUser.getBookedCopyToDueDateMap().put(bookCopy.getBookCopyId(), new LibraryUser.BookOrderDetail(bookCopy, commands[3]));

		bookToUserMap.put(bookCopy.getBookCopyId(), libraryUser);
		userIdToUserData.put(libraryUser.getUserId(), libraryUser);

		System.out.println("Borrowed Book from rack: "+ j);
	}
}
