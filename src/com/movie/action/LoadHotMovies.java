package com.movie.action;

import java.util.Map;

import com.movie.dao.moviePreferenceDAO;
import com.movie.domain.Movie;
import com.movie.domain.MovieList;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoadHotMovies extends ActionSupport {

	/**
	 * @return
	 */
	public String execute() {
		// TODO Auto-generated method stub
		MovieList movieList = moviePreferenceDAO.getMoviesByPreference();
		Map<Movie ,Float>movies = movieList.getMovies();
		if(movies!=null){
			Map request = (Map)ActionContext.getContext().get("request");
			if(request.containsKey("hotmovies"))
				request.remove("hotmovies");
			request.put("hotmovies", movies);
			
			return SUCCESS;
		}
		return null;
		
	}
}