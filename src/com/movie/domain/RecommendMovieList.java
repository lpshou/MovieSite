package com.movie.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import com.movie.dao.movieDAO;
public class RecommendMovieList {
	private List<RecommendMovie> recommendMovies = new ArrayList<RecommendMovie>();
	public RecommendMovieList(){
		
	}
	public RecommendMovieList(List<RecommendedItem> items){
		List<String> movieIDList = new ArrayList<String>();
		for (RecommendedItem item : items){
			movieIDList.add(String.valueOf(item.getItemID()));
		}
		
		Map<String, Movie> movies = movieDAO.getMovieMap(movieIDList);
		
		for (RecommendedItem item : items){
			String movieID = String.valueOf(item.getItemID());
			Movie movie = movies.get(movieID);
			if(movie != null){
				RecommendMovie rm = new RecommendMovie(movie, item.getValue());
				recommendMovies.add(rm);
			}
		}
	}

	public List<RecommendMovie> getRecommendMovies() {
		return recommendMovies;
	}

	public void setRecommendMovies(List<RecommendMovie> recommendMovies) {
		this.recommendMovies = recommendMovies;
	}

}
