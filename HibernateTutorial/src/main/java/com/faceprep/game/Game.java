package com.faceprep.game;

import java.util.Date;

public class Game implements java.io.Serializable {

	private int gameId;
	private String gamename;
	private String createdBy;
	private Date boughtDate;

	public Game() {
	}

	public Game(int gameId, String gamename, String createdBy,
			Date boughtDate) {
		this.gameId = gameId;
		this.gamename = gamename;
		this.createdBy = createdBy;
		this.boughtDate = boughtDate;
	}

	public int getGameId() {
		return this.gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getGamename() {
		return this.gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getBoughtDate() {
		return this.boughtDate;
	}

	public void setBoughtDate(Date boughtDate) {
		this.boughtDate = boughtDate;
	}

}