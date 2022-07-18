package libraryManagementSystem.dao;

import libraryManagementSystem.model.BookCopy;
import libraryManagementSystem.model.Library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author naveen.chauhan on 17/07/22
 */
public class LibraryBookStoreDepartment {
	public void addBookToShelf(String[] commands, Library library) {
		String bookId = commands[1];
		String title = commands[2];
		String authorList = commands[3];
		String publisherList = commands[4];
		String[] bookCopyIdList = commands[5].split(",");

		List<String> authors = new ArrayList<>();
		List<String> publishers = new ArrayList<>();

		Arrays.stream(authorList.split(",")).forEach(author -> authors.add(author));
		Arrays.stream(publisherList.split(",")).forEach(publisher -> publishers.add(publisher));

		ArrayList<Integer> bookedRackIds = new ArrayList<>();
		for (int i = 0; i < bookCopyIdList.length; i++) {
			for (int j = 0; j < library.getRack().length; j++) {
				if (library.getRack()[j] == null) {
					BookCopy bookCopy = new BookCopy(bookCopyIdList[i], bookId, title, authors, publishers);
					library.getRack()[j] = bookCopy;
					bookedRackIds.add(j + 1);
					break;
				}
			}
		}

		if (bookedRackIds.size() < 1) {
			System.out.println("Rack not available");
			return;
		}

		System.out.println("Added Book to racks: " + bookedRackIds.toString()
				.replace("[", "")
				.replace("]", "")
				.trim());


		if (bookCopyIdList.length - bookedRackIds.size() > 0) {
			System.out.println("Rack not available");
		}
	}

	public void searchBookInLibrary(String[] commands, Library library) {
		switch (commands[1]) {
			case "book_id":
				searchByBookId(commands[2], library);
				break;
			case "author_id":
				searchByAuthorId(commands[2], library);
				break;
		}
	}

	private void searchByAuthorId(String authorName, Library library) {
		List<BookCopy> bookCopies = new ArrayList<>();
		List<Integer> rackDetails = new ArrayList<>();
		for (int j = 0; j < library.getRack().length; j++) {

			if (library.getRack()[j] != null && library.getRack()[j].getAuthors().contains(authorName)) {
				bookCopies.add(library.getRack()[j]);
				rackDetails.add(j + 1);
			}
		}

		printBooks(bookCopies, rackDetails);

	}

	private void searchByBookId(String command, Library library) {
		List<BookCopy> bookCopies = new ArrayList<>();
		List<Integer> rackDetails = new ArrayList<>();

		for (int j = 0; j < library.getRack().length; j++) {
			if (library.getRack()[j] != null && library.getRack()[j].getBookId().equals(command)) {
				bookCopies.add(library.getRack()[j]);
				rackDetails.add(j + 1);
			}
		}

		printBooks(bookCopies, rackDetails);
	}

	private void printBooks(List<BookCopy> bookCopies, List<Integer> rackDetails) {
		int rackCounter = 0;
		for (BookCopy bookCopy : bookCopies) {
			System.out.println("Book Copy: " + bookCopy.getBookCopyId() + " " + bookCopy.getBookId() + " " + bookCopy.getAuthors().toString()
					.replace("[", "")
					.replace("]", "")
					.trim() + " " + bookCopy.getPublishers().toString()
					.replace("[", "")
					.replace("]", "")
					.trim() + " " + rackDetails.get(rackCounter));
			rackCounter++;
		}
	}

	public BookCopy removeBookFromLibrary(String bookCopyId, Library library) {
		boolean isRemoved = false;
		BookCopy bookCopy = null;
		for (int i = 0; i < library.getRack().length; i++) {
			if (library.getRack()[i] != null && library.getRack()[i].getBookCopyId().equals(bookCopyId)) {
				bookCopy = library.getRack()[i];
				library.getRack()[i] = null;
				isRemoved = true;
				System.out.println("Removed book copy: " + bookCopyId + " from rack: " + (i + 1));
			}
		}
		if (!isRemoved) {
			System.out.println("Invalid Book Copy ID");
		}
		return bookCopy;
	}

	public void addBookToShelf(BookCopy bookCopy, Library library) {
		int j = 0;
		for (; j < library.getRack().length; j++) {
			if (library.getRack()[j] == null) {
				library.getRack()[j] = bookCopy;
				System.out.println("Added Book to rack: " + j + 1);
				break;
			}
		}

		if (j == library.getRack().length) {
			System.out.println("Rack not available");
		}
	}
}
