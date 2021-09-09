import java.util.Scanner;

public class Library {
	static final int NUMBER_OF_ALL_BOOKS = 50;
	static final int NUMBER_OF_ALL_MEMEBERS = 50;
	static final int MAX_NUMBER_OF_BORROWED_BOOKS_FOR_EACH_MEMBER = 5;
	String[] books = new String[NUMBER_OF_ALL_BOOKS];
	String[] booksNames = new String[books.length];
	int[] booksIds = new int[books.length];
	int[] booksCount = new int[books.length];
	String[] members = new String[NUMBER_OF_ALL_MEMEBERS];
	String[] membersNames = new String[members.length];
	String[] membersBirthDates = new String[members.length];
	int[] membersIds = new int[members.length];
	int[][] membersBooks = new int[members.length][MAX_NUMBER_OF_BORROWED_BOOKS_FOR_EACH_MEMBER];
	int[] membersNumberOfBorrowedBooks = new int[members.length];

	int numberOfAddedBooks = 0;
	int bookId = 1001;
	int numberOfAddedMembers = 0;
	int memberId = 2002;

	public void addBook(String name, int count) {
		if (numberOfAddedBooks < NUMBER_OF_ALL_BOOKS) {
			booksNames[numberOfAddedBooks] = name;
			booksIds[numberOfAddedBooks] = bookId;
			booksCount[numberOfAddedBooks] = count;
			books[numberOfAddedBooks] = "ID: " + bookId + " Name: " + name + " Number of Available books: " + count;
			System.out.println("New book is added: " + books[numberOfAddedBooks]);
			numberOfAddedBooks++;
			bookId++;
		} else
			System.out.println("All books are added.");
	}

	void addMember(String name, String birthDate) {
		if (numberOfAddedMembers < NUMBER_OF_ALL_MEMEBERS) {
			membersNames[numberOfAddedMembers] = name;
			membersBirthDates[numberOfAddedMembers] = birthDate;
			membersIds[numberOfAddedMembers] = memberId;
			members[numberOfAddedMembers] = "ID: " + memberId + " Name: " + name + " Birthdate: "
					+ membersBirthDates[numberOfAddedMembers];
			System.out.println("New member is added: " + members[numberOfAddedMembers]);
			numberOfAddedMembers++;
			memberId++;
		} else
			System.out.println("All members are added.");
	}

	void borrowBook(int memberId, int bookId) {
		int foundMember = -1;
		for (int i = 0; i < membersIds.length; i++)
			if (membersIds[i] == memberId) {
				foundMember = i;
				break;
			}

		if (foundMember == -1) {
			System.out.println("Member not found!");
			return;
		}

		int foundBook = -1;
		for (int i = 0; i < booksIds.length; i++) {
			if (booksIds[i] == bookId) {
				foundBook = i;
				break;
			}
		}

		if (foundBook == -1) {
			System.out.println("Book not exist");
			return;
		}

		for (int i = 0; i < membersNumberOfBorrowedBooks[foundMember]; i++)
			if (membersBooks[foundMember][i] == bookId) {
				System.out.println("This book has already been borrowed by the member.");
				return;
			}

		if (membersNumberOfBorrowedBooks[foundMember] >= MAX_NUMBER_OF_BORROWED_BOOKS_FOR_EACH_MEMBER) {
			System.out.println("MAX books are borrowed by: " + members[foundMember]);
			return;
		}

		if (booksCount[foundBook] == 0) {
			System.out.println("Book is not available: " + books[foundBook]);
			return;
		}

		int borrowedBookIndex = -1;
		for (int i = 0; i < MAX_NUMBER_OF_BORROWED_BOOKS_FOR_EACH_MEMBER; i++)
			if (membersBooks[foundMember][i] <= 0) {
				borrowedBookIndex = i;
				break;
			}
		membersBooks[foundMember][borrowedBookIndex] = bookId;
		membersNumberOfBorrowedBooks[foundMember]++;
		booksCount[foundBook]--;
		books[foundBook] = "ID: " + bookId + " Name: " + booksNames[foundBook] + " Number of Available books: "
				+ booksCount[foundBook];
		System.out.println("Book: " + books[foundBook] + " borrowed by Members: " + members[foundMember]);
	}

	void returnBook(int memberId, int bookId) {
		int foundMember = -1;
		for (int i = 0; i < membersIds.length; i++)
			if (membersIds[i] == memberId) {
				foundMember = i;
				break;
			}

		if (foundMember == -1) {
			System.out.println("Member not found!");
			return;
		}

		int foundBook = -1;
		for (int i = 0; i < booksIds.length; i++) {
			if (booksIds[i] == bookId) {
				foundBook = i;
				break;
			}
		}

		if (foundBook == -1) {
			System.out.println("Book not exist");
			return;
		}

		if (membersNumberOfBorrowedBooks[foundBook] == 0) {
			System.out.println("No books are borrowed by: " + members[foundMember]);
			return;
		}
		int returnedBookIndex = -1;
		for (int i = 0; i < MAX_NUMBER_OF_BORROWED_BOOKS_FOR_EACH_MEMBER; i++)
			if (membersBooks[foundMember][i] == bookId) {
				returnedBookIndex = i;
				break;
			}
		if(returnedBookIndex == -1) {
			System.out.println("This book is not borrowed by: " + members[foundMember]);
			return;
		}
		membersBooks[foundMember][returnedBookIndex] = -1;
		membersNumberOfBorrowedBooks[foundMember]--;
		booksCount[foundBook]++;
		books[foundBook] = "ID: " + bookId + " Name: " + booksNames[foundBook] + " Number of Available books: "
				+ booksCount[foundBook];
		System.out.println("Book: " + books[foundBook] + " returned by Members: " + members[foundMember]);
	}

	void showBooksStatus() {
		if (numberOfAddedBooks == 0) {
			System.out.println("No book is added.");
			return;
		}

		for (int i = 0; i < numberOfAddedBooks; i++)
			System.out.println("# "+books[i]);
	}

	void showMembersStatus() {
		if (numberOfAddedMembers == 0) {
			System.out.println("No member is added.");
			return;
		}

		for (int i = 0; i < numberOfAddedMembers; i++) {
			System.out.println("# "+ members[i]);
			if (membersNumberOfBorrowedBooks[i] == 0) {
				System.out.println("NOT borrowed any books.");
			} else {
				System.out.println(" - List of Borrowed books: ");
				for (int j = 0; j < MAX_NUMBER_OF_BORROWED_BOOKS_FOR_EACH_MEMBER; j++) {
					for (int k = 0; k < numberOfAddedBooks; k++)
						if (booksIds[k] == membersBooks[i][j])
							System.out.println("   -"+books[k]);
				}
			}
		}
	}

	public static void main(String[] args) {
		Library library = new Library();
		Scanner input = new Scanner(System.in);
		while (true) {
			showMenu();
			int menu = input.nextInt();
			switch (menu) {
			case 1:
				System.out.println("Enter new book name:");
				String newBookName = input.next();
				System.out.println("Enter number of new book:");
				int newBookCount = input.nextInt();
				library.addBook(newBookName, newBookCount);
				break;
			case 2:
				System.out.println("Enter member name: ");
				String newMemberName = input.next();
				System.out.println("Enter birthdate (Ex. 01-01-2020):");
				String newBirthDate = input.next();
				library.addMember(newMemberName, newBirthDate);
				break;
			case 3:
				System.out.println("Enter id of member:");
				int memberId = input.nextInt();
				System.out.println("Enter book id:");
				int bookId = input.nextInt();
				library.borrowBook(memberId, bookId);
				break;
			case 4:
				System.out.println("Enter id of member:");
				int borrowerId = input.nextInt();
				System.out.println("Enter book id:");
				int borrowedBookId = input.nextInt();
				library.returnBook(borrowerId, borrowedBookId);
				break;
			case 5:
				library.showBooksStatus();
				break;
			case 6:
				library.showMembersStatus();
				break;
			case 7:
				input.close();
				return;
			}
		}

	}

	static void showMenu() {
		System.out.println("1- add book");
		System.out.println("2- add member");
		System.out.println("3- borrow book");
		System.out.println("4- return book");
		System.out.println("5- show all books status");
		System.out.println("6- show all members status");
		System.out.println("7- exit");
	}
}
