package com.movie.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.movie.dao.movieFavoriteDAO;
import com.movie.dao.moviePreferenceDAO;
import com.movie.dao.userDAO;
import com.movie.domain.Movie;
import com.movie.domain.MovieList;
import com.movie.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoadUserMovies extends ActionSupport {
	/**
	 * @return
	 */
	public String execute() {
		Map session = ActionContext.getContext().getSession();
		User user = (User)session.get("user");
		//获得用户评分的电影
		MovieList movieList = moviePreferenceDAO.getMoviesByUserID(String.valueOf(user.getId()));
		Map<Movie ,Float>movies = movieList.getMovies();
		
		//获得每一部电影的一张海报
		
		//获得用户收藏的电影
		List<Movie>favoriteMovies = new ArrayList<Movie>();
		favoriteMovies = movieFavoriteDAO.getMoviesByUserID(user.getId());
		
		if(movies!=null){
			Map request = (Map)ActionContext.getContext().get("request");
			if(request.containsKey("movies"))
				request.remove("movies");
			request.put("movies", movies);
			
			if(request.containsKey("user"))
				request.remove("user");
			request.put("user", user);
			
			if(request.containsKey("favoriteMovies"))
				request.remove("favoriteMovies");
			request.put("favoriteMovies", favoriteMovies);
			
			return SUCCESS;
		}
		
		return null;
		// TODO Auto-generated method stub

	}
}