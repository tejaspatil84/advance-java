package com.jspiders.hibernatemusicplayer.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "playlist1")
public class SongDTO {
	@Id
	@Column(name = "song_id")
	private int id;
	@Column(name = "song_name")
	private String name;
	@Column(name = "singer_name")
	private String singerName;
	private int duration;
}
