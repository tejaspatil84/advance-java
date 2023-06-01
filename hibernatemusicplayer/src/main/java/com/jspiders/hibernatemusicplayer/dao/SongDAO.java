package com.jspiders.hibernatemusicplayer.dao;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jspiders.hibernatemusicplayer.dto.SongDTO;

public class SongDAO {
	
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static Query query;
	private static String jpql;
	private static int result;
	
	private static Scanner scanner=new Scanner(System.in);
	private static int choice;
	private static int id;
	private static String songName;
	private static String singerName;
	private static int duration;
	
	private static void openConnection() {
		factory=Persistence.createEntityManagerFactory("musicPlayer");
		manager=factory.createEntityManager();
		transaction=manager.getTransaction();
	}
	
	private static void closeConnecton() {
		if (factory!=null) {
			factory.close();
		}
		if (manager!=null) {
			manager.close();
		}
		if (transaction.isActive()) {
			transaction.rollback();
		}
	}
	
	private static void addSong(int id, String songName, String singerName, int duration) {
		try {
			openConnection();
			transaction.begin();
			
			SongDTO song=new SongDTO();
			song.setId(id);
			song.setName(songName);
			song.setSingerName(singerName);
			song.setDuration(duration);
			manager.persist(song);
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnecton();
		}
	}
	
	private static void removeSong(int sid) {
		try {
			openConnection();
			transaction.begin();
			
			jpql="DELETE FROM SongDTO WHERE id="+sid;
			query=manager.createQuery(jpql);
			result=query.executeUpdate();
			System.out.println(result+" song deleted successfully");
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnecton();
		}
	}
	
	private static void editSong(int sid, String songName, String singerName, int duration) {
		try {
			openConnection();
			transaction.begin();
			
			jpql="update SongDTO set name='"+songName+"', singerName='"+singerName+"', duration="+duration+" where id="+sid;
			System.out.println(jpql);
			query=manager.createQuery(jpql);
			result=query.executeUpdate();
			System.out.println(result+" song edited successfully");
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnecton();
		}
	}
	
	private static void playAllSongs() {
		try {
			openConnection();
			transaction.begin();
			
			jpql="from SongDTO";
			query=manager.createQuery(jpql);
			List<SongDTO> songs=query.getResultList();
			for(SongDTO song: songs) {
				System.out.println(song);
			}
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnecton();
		}
	}
	
	private static void playRandomeSongs() {
		try {
			openConnection();
			transaction.begin();
			
			jpql="from SongDTO order by id desc";
			query=manager.createQuery(jpql);
			List<SongDTO> songs=query.getResultList();
			int lastId=0;
			for(SongDTO song: songs) {
				lastId=song.getId();
				break;
			}
			System.out.println(lastId);
			for (int i = 1; i <= lastId; i++) {
				int sid=(int) (Math.random()*lastId);
				jpql="from SongDTO where id="+sid;
				query=manager.createQuery(jpql);
				List<SongDTO> songs1=query.getResultList();
				for(SongDTO song: songs1) {
					System.out.println(song);
				}
			}
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnecton();
		}
	}
	
	private static void chooseToPlaySong(int sid) {
		try {
			openConnection();
			transaction.begin();
			
			jpql="from SongDTO where id="+sid;
			query=manager.createQuery(jpql);
			List<SongDTO> songs=query.getResultList();
			for(SongDTO song: songs) {
				System.out.println(song);
			}
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnecton();
		}
	}
	
	public static void main(String[] args) {
		boolean exit=true;
		while (exit) {
			System.out.println("======== MENU ======== \n 1. Play Song \n 2. Add/Remove Song \n 3. Edit song \n 4. Exit");
			choice=scanner.nextInt();
			switch (choice) {
			case 1:{
				boolean exit2=true;
				while (exit2) {
					System.out.println(" 1. Play All Songs \n 2. Play Randome Songs \n 3. Choose to Play \n 4. Go Back");
					choice=scanner.nextInt();
					switch (choice) {
					case 1:{
						playAllSongs();
					}
						break;
					case 2:{
						playRandomeSongs();
					}
						break;
					case 3:{
						playAllSongs();
						System.out.println("Enter id to play song");
						chooseToPlaySong(scanner.nextInt());
					}
						break;
					case 4:{
						exit2=false;
					}
						break;

					default: System.out.println("Invalid choice...");
						break;
					}
				}
			}
				break;
			case 2:{
				boolean exit2=true;
				while (exit2) {
					System.out.println(" 1. Add Song \n 2. Remove Song \n 3. Go Back");
					choice=scanner.nextInt();
					switch (choice) {
					case 1:{
						System.out.println("Enter song id");
						id=scanner.nextInt();
						scanner.nextLine();
						System.out.println("Enter song name");
						songName=scanner.nextLine();
						System.out.println("Enter singer name");
						singerName=scanner.nextLine();
						System.out.println("Enter song duration");
						duration=scanner.nextInt();
						addSong(id, songName, singerName, duration);
					}						
						break;
					case 2:{
						System.out.println("Enter id");
						int id=scanner.nextInt();
						removeSong(id);
					}						
						break;
					case 3:{
						exit2=false;
					}						
					break;

					default:
						break;
					}
				}
			}
				break;
			case 3:{
				playAllSongs();
				System.out.println("Enter song id to edit song");
				id=scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter song name");
				songName=scanner.nextLine();
				System.out.println("Enter singer name");
				singerName=scanner.nextLine();
				System.out.println("Enter song duration");
				duration=scanner.nextInt();
				editSong(id, songName, singerName, duration);
				
			}
				break;
			case 4:{
				exit=false;
				System.out.println("Thank You..!!");
			}
				break;

			default: System.out.println("Invalid choice...");
				break;
			}
		}
	}

}
