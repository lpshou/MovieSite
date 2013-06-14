package com.movie.domain;

import java.util.HashMap;
import java.util.Map;

public class MovieList {
	public static final String MOVIE = "movie";
	public static final String VALUE = "score";
	
	private Map<Movie, Float> movies = new HashMap<Movie, Float>();
	
	public Map<Movie, Float> getMovies() {
		return movies;
	}

	public void setMovies(Map<Movie, Float> movies) {
		this.movies = movies;
	}

	public void add(Movie m, Float f){
		movies.put(m, f);
	}
	
	public Float get(Movie m){
		return movies.get(m);
	}
	
	public Float remove(Movie m){
		return movies.remove(m);
	}
	

}
