package com.movie.domain;

public class RecommendMovie {

	private Movie movie;
	private float value;
	
	public RecommendMovie(Movie movie, float value){
		this.movie = movie;
		this.value = value;
	}
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}

}
