package com.jspiders.musicplayer.operations;

import java.util.Scanner;

import com.jspiders.musicplayer.entity.PlayList;
import com.jspiders.musicplayer.entity.Song;

public class SongOpertions {
	public static Scanner sc=new Scanner(System.in);
	Song song=new Song();
	PlayList playList=new PlayList();
	String songName;
	int sid;
	String singerName;
	double sduration;
	public void addSong(){
		System.out.println("Enter the name of the song");
		songName=sc.nextLine();
		System.out.println("Enter ID");
		sid=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the name of the singer");
		singerName=sc.nextLine();
		System.out.println("Enter the duration of the song");
		sduration=sc.nextDouble();
		sc.nextLine();
	}
	public void addSongToPlayList(){
		addSong();
		System.out.println(playList.addSongs(new Song(songName, sid, singerName, sduration)));
	}
	public void displayAllSongs() {
		playList.displayAllSongs();
	}
	public void removeSong() {
		System.out.println(playList.removeSong());
	}
	public void chooseSongToPlay(int songno) {
		System.out.println(playList.chooseSongToPlay(songno));
	}
	public void playRandom() {
		playList.playRandom();
	}
	public void editSong(int sno){
		if(playList.arrayListSize()!=0) {
			addSong();
			System.out.println(playList.editSong(sno, new Song(songName, sid, singerName, sduration)));
		}
	}
	public int arrayListSize() {
		return playList.arrayListSize();
	}
	
}
