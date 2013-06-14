package com.movie.domain;

public class MovieSimilarity {
	private Integer movieID1;
	private Integer movieID2;
	private double similarity;
	public Integer getMovieID1() {
		return movieID1;
	}
	public void setMovieID1(Integer movieID1) {
		this.movieID1 = movieID1;
	}
	public Integer getMovieID2() {
		return movieID2;
	}
	public void setMovieID2(Integer movieID2) {
		this.movieID2 = movieID2;
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	} 

}
