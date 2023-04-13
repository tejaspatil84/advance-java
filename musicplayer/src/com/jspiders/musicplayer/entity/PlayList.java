package com.jspiders.musicplayer.entity;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayList {
	Scanner sc=new Scanner(System.in);
	private ArrayList<Song> songs=new ArrayList<Song>();
	
//	To return ArrayList Size
	public int arrayListSize() {
		return songs.size();
	}
	
//	To Add Song Inside An ArrayList
	public String addSongs(Song s) {
		songs.add(s);
		return "Song Added successfully..!";
	}
//	To Play All Song OR To Display All Songs
	public void displayAllSongs() {
		if(songs.size()>0) {
			for(int i=0;i<songs.size();i++) {
				int songno=i+1;
				System.out.println(songno+" "+songs.get(i));
			}
		}
		else
			System.out.println("Songs are not available..");
	}
//	To Remove Song From ArrayList
	public String removeSong() {
		System.out.println("Select song number to remove");
		displayAllSongs();
		int songno=sc.nextInt();
		try {
			songs.remove(songno-1);
			return "Song removed Successfully";
		} catch (IndexOutOfBoundsException iobe) {
			return "Invalid song number...Please Try again";
		}
	}
//	To Choose song to Play
	public String chooseSongToPlay(int songno) {
		if(songs.size()>0) {
			try {
				return " "+songs.get(songno-1);
			}
			catch (IndexOutOfBoundsException iobe) {
				return "Invalid song number...Please Try again";
			}
		}
		else
			return "Songs are not present..";		
	}
//	To Play Random Song
	public void playRandom() {
		if(songs.size()>0)
		{
			for(int i=0;i<songs.size();i++)
			{
				int rno= (int)(Math.random()*songs.size());
				System.out.println(songs.get(rno));
			}
		}
		else
			System.out.println("Songs are not present..");
	}
//	To Edit Song
	public String editSong(int sno, Song s) {
		try {
			songs.set(sno-1, s);
			return "Song Edited successfully..!";
		} catch (Exception e) {
			return "Invalid song number...Please Try again";
		}
		
	} 
}
