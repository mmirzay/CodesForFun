package creational.builder;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private String address;
	private String phoneNumber;

	private User(UserBuilder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.email = builder.email;
		this.address = builder.address;
		this.phoneNumber = builder.phoneNumber;
	}

	public static UserBuilder getBuilder(int id, String firstName) {
		return new UserBuilder(id, firstName);
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public static class UserBuilder {
		private int id;
		private String firstName;
		private String lastName;
		private int age;
		private String email;
		private String address;
		private String phoneNumber;

		private UserBuilder(int id, String firstName) {
			this.id = id;
			this.firstName = firstName;
		}

		public UserBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public UserBuilder withAge(int age) {
			this.age = age;
			return this;
		}

		public UserBuilder withEmail(String email) {
			this.email = email;
			return this;
		}

		public UserBuilder withAddress(String address) {
			this.address = address;
			return this;
		}

		public UserBuilder withPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
}
