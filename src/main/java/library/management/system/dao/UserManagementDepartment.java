package library.management.system.dao;

import library.management.system.model.Library;
import library.management.system.model.LibraryUser;

/**
 * @author naveen.chauhan on 17/07/22
 */
public class UserManagementDepartment {
	public void showBorrowedBookByUser(String[] commands, Library library) {
		LibraryUser libraryUser = library.getLibraryUserMap().get(commands[1]);
		if (libraryUser != null) {
			libraryUser.getBookedCopyToDueDateMap().values()
					.forEach(bookOrderDetail -> {
						System.out.println("Book Copy: " + bookOrderDetail.getBookCopy().getBookCopyId() + " " + bookOrderDetail.getDueDate());
					});
		}
	}
}
