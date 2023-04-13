package com.jspiders.musicplayer.main;

import java.util.Scanner;

import com.jspiders.musicplayer.operations.SongOpertions;

public class MusicPlayer {
	private static Scanner sc=new Scanner(System.in);
	private static int choice;
	private static SongOpertions songOp=new SongOpertions();
	
	public static void main(String[] args) {
		boolean exit=true;
		while(exit) {
			System.out.println("========MENU========= \n 1.Play Song \n 2.Add/Remove Song \n 3.Edit Song \n 4.Exit  ");
			choice=sc.nextInt();
			switch(choice) {
			case 1:{
				boolean exit2=true;
				 while(exit2){
					 System.out.println(" 1.Play All Songs \n 2.Play Random Song \n 3.Choose song to play \n 4.Go Back");
						choice=sc.nextInt();
					switch(choice) {
					case 1:{
						songOp.displayAllSongs();
					}
					break;
					case 2:{
						songOp.playRandom();
					}
					break;
					case 3:{
						if(songOp.arrayListSize()!=0) {
							System.out.println("Enter Number of song to play..");
							songOp.displayAllSongs();
							int songno=sc.nextInt();
							songOp.chooseSongToPlay(songno);
						}
						else
							System.out.println("Songs are not available...");
					}
					break;
					case 4:{
						exit2=false;
					}
					break;
					default:System.out.println("Invalid Choice Please try again..!!");
					}
				}
			}
			break;
			case 2:{
				Boolean exit2=true;
				while (exit2) {
					System.out.println(" 1.Add Song \n 2.Remove Song \n 3.Go Back");
					choice=sc.nextInt();
					switch(choice) {
					case 1:{
						songOp.addSongToPlayList();
					}
					break;
					case 2:{
						if(songOp.arrayListSize()!=0) {
							songOp.removeSong();
						}
						else
							System.out.println("Songs are not available...");
					}
					break;
					case 3:{
						exit2=false;
					}
					break;
					default:System.out.println("Invalid Choice Please try again..!!");
					}
				}
			}
			break;
			case 3:{
				if(songOp.arrayListSize()!=0) {
					System.out.println("Select song Number to edit");
					songOp.displayAllSongs();
					int songno=sc.nextInt();
					songOp.editSong(songno);
				}
				else
					System.out.println("Songs are not available...");
			}
			break;
			case 4:{
				exit=false;
				System.out.println("Thank you..!!");
			}
			break;
			default :System.out.println("Invalid Choice Please try again..!!");
			}
		}
	}
}
