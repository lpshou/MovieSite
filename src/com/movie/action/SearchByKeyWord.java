package com.movie.action;

import java.util.List;
import java.util.Map;

import com.movie.dao.movieDAO;
import com.movie.domain.Movie;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SearchByKeyWord extends ActionSupport {

	private String keyword;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * @return
	 */
	public String execute() {
		// TODO Auto-generated method stub
		if(keyword!=null){
			List<Movie>movies = movieDAO.getMovieByMovieKeyword(keyword);
			if(movies.size()>0){
				Map request = (Map)ActionContext.getContext().get("request");
				request.put("movies", movies);
			}
			System.out.println("keyword: "+keyword);
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
}