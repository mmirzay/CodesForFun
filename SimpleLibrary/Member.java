
public class Member {
	private int id;
	private String name;
	private String birthdate;
	private Book[] borrowedBooks = new Book[5];
	private int borrowedBookCounter;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Book[] getBorrowedBooks() {
		return borrowedBooks;
	}

	public boolean borrowBook(Book book) {
		if (borrowedBookCounter == borrowedBooks.length) {
			System.out.println("MAX books are borrowed by: " + "ID: " + this.id + " Name: " + this.name + " Birthdate: "
					+ this.birthdate);
			return false;
		}

		for (int i = 0; i < borrowedBooks.length; i++)
			if (borrowedBooks[i] != null && borrowedBooks[i].getId() == book.getId()) {
				System.out.println("This book has already been borrowed by the member.");
				return false;
			}
		int index = -1;
		for(int i=0;i<borrowedBooks.length;i++)
			if(borrowedBooks[i]== null)
				index = i;
		borrowedBooks[index] = book;
		borrowedBookCounter++;
		System.out.println("Book borrowed by Members: " + "ID: " + this.id + " Name: " + this.name + " Birthdate: "
				+ this.birthdate);
		return true;
	}

	public boolean returnBook(Book book) {
		if (borrowedBookCounter == 0) {
			System.out.println("No books are borrowed by: " + "ID: " + this.id + " Name: " + this.name + " Birthdate: "
					+ this.birthdate);
			return false;
		}

		int index = -1;
		for (int i = 0; i < borrowedBooks.length; i++)
			if (borrowedBooks[i] != null && borrowedBooks[i].getId() == book.getId()) {
				index = i;
				break;
			}
		if (index == -1) {
			System.out.println("This book is not borrowed by: " + "ID: " + this.id + " Name: " + this.name
					+ " Birthdate: " + this.birthdate);
			return false;
		}

		borrowedBooks[index] = null;
		borrowedBookCounter--;
		System.out.println("Book returned by Members: " + "ID: " + this.id + " Name: " + this.name + " Birthdate: "
				+ this.birthdate);
		return true;
	}
}
