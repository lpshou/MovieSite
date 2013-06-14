package com.movie.domain;

public class MoviePreference {
	private Integer userID;
	private Integer movieID;
	private Integer preference;
	private Integer timestamp;
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getMovieID() {
		return movieID;
	}
	public void setMovieID(Integer movieID) {
		this.movieID = movieID;
	}
	public Integer getPreference() {
		return preference;
	}
	public void setPreference(Integer preference) {
		this.preference = preference;
	}
	public Integer getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}
	


}
