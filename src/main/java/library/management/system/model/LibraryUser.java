package library.management.system.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author naveen.chauhan on 17/07/22
 */
public class LibraryUser {
	private String userId;
	private String userName;
	private Map<String, BookOrderDetail> bookedCopyToDueDateMap;

	public static class BookOrderDetail {
		BookCopy bookCopy;
		String dueDate;

		public BookOrderDetail(BookCopy bookCopy, String dueDate) {
			this.bookCopy = bookCopy;
			this.dueDate = dueDate;
		}

		public BookCopy getBookCopy() {
			return bookCopy;
		}

		public String getDueDate() {
			return dueDate;
		}
	}

	public LibraryUser(String userId) {
		this.userId = userId;
		this.bookedCopyToDueDateMap = new HashMap<>();
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public Map<String, BookOrderDetail> getBookedCopyToDueDateMap() {
		return bookedCopyToDueDateMap;
	}
}
