package creational.builder;

public class BuilderDemo {

	public static void main(String[] args) {
		User user = User.getBuilder(1, "user")
				.withAge(10)
				.build();

		User user2 = User.getBuilder(2, "user2").build();

		User user3 = User.getBuilder(3, "user3")
				.withAddress("Java")
				.withPhoneNumber("0987654321")
				.build();
	}
}
