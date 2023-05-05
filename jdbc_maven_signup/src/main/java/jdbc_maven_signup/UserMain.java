package jdbc_maven_signup;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserMain {
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
		Scanner scanner = new Scanner(System.in);

		User user = new User();
		UserCRUD crud = new UserCRUD();

		boolean exit = true;
		do {
			System.out.println("Enter your choice. \n 1. Sign Up \n 2. Login \n 3. Exit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				System.out.println("Enter user id");
				int id = scanner.nextInt();
				System.out.println("Enter user name");
				String name = scanner.next();
				System.out.println("Enter user email");
				String email = scanner.next();
				System.out.println("Enter user password");
				String password = scanner.next();
				System.out.println("Enter user address");
				String address = scanner.next();

				user.setId(id);
				user.setName(name);
				user.setEmail(email);
				user.setPassword(password);
				user.setAddress(address);

				crud.signUp(user);
			}
				break;

			case 2: {
				System.out.println("Enter email");
				String email = scanner.next();
				System.out.println("Enter Password");
				String password = scanner.next();
				boolean result = crud.login(email, password);

				if (result) {
					System.out.println("Logged In Successfully...");
					boolean exit1 = true;
					while (exit1) {
						System.out.println(
								"Enter your choice. \n 1. Add Facebook password \n 2. Add WhatsApp password \n 3. Add Snapchat password \n 4. Add Twiter password \n 5. Show all Password \n 6. Exit");
						int choice1 = scanner.nextInt();
						switch (choice1) {
						case 1: {
							System.out.println("Enter Password of Facebook");
							String password1 = scanner.next();
							crud.updateFacebookPassword(password1, email);
						}
							break;
						case 2: {
							System.out.println("Enter Password of WhatsApp");
							String password1 = scanner.next();
							crud.updateWhatsAppPassword(password1, email);
						}
							break;
						case 3: {
							System.out.println("Enter Password of Snapchat");
							String password1 = scanner.next();
							crud.updateSnapchatPassword(password1, email);
						}
							break;
						case 4: {
							System.out.println("Enter Password Twiter");
							String password1 = scanner.next();
							crud.updateTwiterPassword(password1, email);
						}
							break;
						case 5: {
							crud.showAll(email);
						}
							break;

						default:
							exit1 = false;
							break;
						}
					}

				} else {
					System.out.println("Please check email and password");
					System.out.println("Forgot password enter 1 or else 2");
					int forgot = scanner.nextInt();
					if (forgot == 1) {
						System.out.println("Enter email");
						String femail = scanner.next();
						boolean forgotResult = crud.forgotPassword(femail);
						if (forgotResult == true) {
							System.out.println("Enter New Password");
							String newPassword = scanner.next();
							crud.updatePassword(newPassword, femail);
						} else {
							System.out.println("Invalid email");
						}
					}
				}

			}
				break;
			case 3: {
				exit = false;
				System.out.println("Thank You...");
			}
				break;

			default:
				System.out.println("Invalid choice...");
				break;
			}

		} while (exit);

		scanner.close();
	}
}
