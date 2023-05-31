package com.jspiders.jdbcmusicplayer.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.jspiders.jdbcmusicplayer.operations.SongOperations;

public class MusicPlayer {
	private static Scanner scanner = new Scanner(System.in);
	private static int choice;
	private static SongOperations operations = new SongOperations();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		boolean exit = true;
		while (exit) {
			System.out
					.println("======== Menu ======== \n 1.Play Song \n 2.Add/Remove Song \n 3.Edit Song \n 4.Exit");
			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				boolean exit2 = true;
				while (exit2) {
					System.out.println(" 1. Play All Songs \n 2. Play Randome Songs \n 3. Choose to Play \n 4. Go Back");
					choice = scanner.nextInt();
					switch (choice) {
					case 1: {
						operations.playAllSong();
					}
						break;
					case 2: {
						operations.playRandomeSong();
					}
						break;
					case 3: {
						System.out.println("Enter song id to play song");
						operations.playAllSong();
						int id = scanner.nextInt();
						operations.chooseToPlay(id);
					}
						break;
					case 4: {
						exit2 = false;
					}
						break;

					default:
						System.out.println("Invalid choice...");
						break;
					}

				}
			}
				break;
			case 2: {
				boolean exit2 = true;
				while (exit2) {
					System.out.println(" 1. Add Song \n 2. Remove Song \n 3. Go back");
					choice = scanner.nextInt();
					switch (choice) {
					case 1: {
						operations.addSong();
					}
						break;
					case 2: {
						System.out.println("Enter id for remove song");
						int id = scanner.nextInt();
						operations.removeSong(id);
					}
						break;
					case 3: {
						exit2 = false;
					}
						break;

					default:
						System.out.println("Invalid choice...");
						break;
					}

				}

			}
				break;
			case 3: {
				System.out.println("Enter song id to edit");
				operations.playAllSong();
				int id = scanner.nextInt();
				operations.editSong(id);
			}
				break;
			case 4: {
				exit = false;
				System.out.println("Thank You..!!");
			}
				break;

			default:
				System.out.println("Invalid choice...");
				break;
			}

		}

	}

}
