import java.util.Scanner;

public class Library {
	static final int NUMBER_OF_ALL_BOOKS = 50;
	static final int NUMBER_OF_ALL_MEMEBERS = 50;
	private Book[] books = new Book[NUMBER_OF_ALL_BOOKS];
	private int[] booksCount = new int[books.length];
	private Member[] members = new Member[NUMBER_OF_ALL_MEMEBERS];

	int numberOfAddedBooks = 0;
	int bookId = 1001;
	int numberOfAddedMembers = 0;
	int memberId = 2002;

	public void addBook(String name, int count) {
		if (numberOfAddedBooks == NUMBER_OF_ALL_BOOKS) {
			System.out.println("All books are added.");
			return;
		}
		Book book = new Book();
		book.setId(bookId);
		book.setName(name);
		books[numberOfAddedBooks] = book;
		booksCount[numberOfAddedBooks] = count;
		numberOfAddedBooks++;
		bookId++;
		System.out.println(
				"New book is added: " + "ID: " + book.getId() + " Name: " + book.getName() + " Count: " + count);
	}

	void addMember(String name, String birthDate) {
		if (numberOfAddedMembers == NUMBER_OF_ALL_MEMEBERS) {
			System.out.println("All members are added.");
			return;
		}
		Member member = new Member();
		member.setId(memberId);
		member.setName(name);
		member.setBirthdate(birthDate);
		members[numberOfAddedMembers] = member;
		numberOfAddedMembers++;
		memberId++;
		System.out.println("New member is added: " + "ID: " + member.getId() + " Name: " + member.getName()
				+ " Birthdate: " + member.getBirthdate());
	}

	void borrowBook(int memberId, int bookId) {
		Member foundMember = null;
		for (int i = 0; i < numberOfAddedMembers; i++)
			if (members[i] != null && members[i].getId() == memberId) {
				foundMember = members[i];
				break;
			}

		if (foundMember == null) {
			System.out.println("Member not found!");
			return;
		}

		Book foundBook = null;
		for (int i = 0; i < numberOfAddedBooks; i++) {
			if (books[i].getId() == bookId) {
				foundBook = books[i];
				break;
			}
		}

		if (foundBook == null) {
			System.out.println("Book not exist");
			return;
		}

		for (int i = 0; i < numberOfAddedBooks; i++)
			if (books[i].getId() == bookId)
				if (booksCount[i] == 0) {
					System.out.println(
							"Book is not available: " + "ID: " + foundBook.getId() + " Name: " + foundBook.getName());
					return;
				}

		if (foundMember.borrowBook(foundBook) == true) {
			for (int i = 0; i < numberOfAddedBooks; i++)
				if (books[i].getId() == bookId) {
					booksCount[i]--;
					break;
				}
		}
	}

	void returnBook(int memberId, int bookId) {
		Member foundMember = null;
		for (int i = 0; i < numberOfAddedMembers; i++)
			if (members[i] != null && members[i].getId() == memberId) {
				foundMember = members[i];
				break;
			}

		if (foundMember == null) {
			System.out.println("Member not found!");
			return;
		}

		Book foundBook = null;
		for (int i = 0; i < numberOfAddedBooks; i++) {
			if (books[i].getId() == bookId) {
				foundBook = books[i];
				break;
			}
		}

		if (foundBook == null) {
			System.out.println("Book not exist");
			return;
		}

		if (foundMember.returnBook(foundBook) == true) {
			for (int i = 0; i < books.length; i++)
				if (books[i] != null && books[i].getId() == bookId) {
					booksCount[i]++;
					break;
				}
		}
	}

	void showBooksStatus() {
		if (numberOfAddedBooks == 0) {
			System.out.println("No book is added.");
			return;
		}

		for (int i = 0; i < numberOfAddedBooks; i++)
			System.out.println(
					"# " + "ID: " + books[i].getId() + " Name: " + books[i].getName() + " Count: " + booksCount[i]);
	}

	void showMembersStatus() {
		if (numberOfAddedMembers == 0) {
			System.out.println("No member is added.");
			return;
		}

		for (int i = 0; i < numberOfAddedMembers; i++) {
			System.out.println("# " + "ID: " + members[i].getId() + " Name: " + members[i].getName() + " Birthdate: "
					+ members[i].getBirthdate());
			boolean isAnyBorrowedBook = false;
			for (int j = 0; j < members[i].getBorrowedBooks().length; j++)
				if (members[i].getBorrowedBooks()[j] != null) {
					isAnyBorrowedBook = true;
					break;
				}
			if (isAnyBorrowedBook == false) {
				System.out.println("    NOT borrowed any books.");
			} else {
				System.out.println(" - List of Borrowed books: ");
				for (int j = 0; j < members[i].getBorrowedBooks().length; j++)
					if (members[i].getBorrowedBooks()[j] != null)
						System.out.println("   -ID: " + members[i].getBorrowedBooks()[j].getId() + " Name: "
								+ members[i].getBorrowedBooks()[j].getName());
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
