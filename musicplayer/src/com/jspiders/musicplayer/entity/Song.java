package com.jspiders.musicplayer.entity;

public class Song {
	private String songName;
	private int songID;
	private String singer;
	private double duration;
	public Song() {
		
	}
	public Song(String songName, int songID, String singer, double duration) {
		super();
		this.songName = songName;
		this.songID = songID;
		this.singer = singer;
		this.duration = duration;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public int getSongID() {
		return songID;
	}
	public void setSongID(int songID) {
		this.songID = songID;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "Song [songName=" + songName + ", songID=" + songID + ", singer=" + singer + ", duration=" + duration
				+ "]";
	}
	
}
