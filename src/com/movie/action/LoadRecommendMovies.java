package com.movie.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

import com.movie.domain.Movie;
import com.movie.domain.RecommendMovie;
import com.movie.domain.RecommendMovieList;
import com.movie.domain.User;
import com.movie.recommender.MovieRecommenderSingleton;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoadRecommendMovies extends ActionSupport {
	
	private Recommender recommender;
	/**
	 * @return
	 */
	public String execute() {
		// TODO Auto-generated method stub
		String recommenderClassName = "com.movie.recommender.UserBasedRecommender";
		try {
			MovieRecommenderSingleton.initializeIfNeeded(recommenderClassName);
		} catch (TasteException te) {
		}
		recommender = MovieRecommenderSingleton.getInstance().getRecommender();
		
		Map session = ActionContext.getContext().getSession();
		User user = (User)session.get("user");
		long userID = user.getId().longValue();
		int howMany = 10;
		
		try {
			List<RecommendedItem> items = recommender
					.recommend(userID, howMany);
			RecommendMovieList movieList = new RecommendMovieList(items);
			List<RecommendMovie>recommendMovie = movieList.getRecommendMovies();
			Map<Movie ,Float>recommendMovies = new HashMap<Movie,Float>();
			for(RecommendMovie rm :recommendMovie){
				Movie movie = rm.getMovie();
				float f = rm.getValue();
				recommendMovies.put(movie, f);	
			}	
			
			Map request = (Map)ActionContext.getContext().get("request");
			request.put("recommendMovies", recommendMovies);
			return SUCCESS;
		} catch (TasteException te) {
		} 
		return ERROR;
		
	}
}