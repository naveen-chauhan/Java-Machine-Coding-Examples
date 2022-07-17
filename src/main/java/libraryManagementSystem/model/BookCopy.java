package libraryManagementSystem.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author naveen.chauhan on 17/07/22
 */
public class BookCopy {
	private String bookCopyId;
	private String bookId;
	private String title;
	private List<String> authors;
	private List<String> publishers;

	public BookCopy(String bookCopyId, String bookId, String title, List<String> authors, List<String> publishers) {
		this.bookCopyId = bookCopyId;
		this.bookId = bookId;
		this.title = title;
		this.authors = authors;
		this.publishers = publishers;
	}

	public String getBookCopyId() {
		return bookCopyId;
	}

	public String getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public List<String> getPublishers() {
		return publishers;
	}
}
