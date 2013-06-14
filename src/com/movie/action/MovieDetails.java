package com.movie.action;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.s3.model.Bucket;
import com.movie.ceph.ceph;
import com.movie.dao.movieDAO;
import com.movie.dao.movieImageDAO;
import com.movie.domain.Movie;
import com.movie.domain.MovieImage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MovieDetails extends ActionSupport {
	private String movieID="1";
	public String getMovieID() {
		return movieID;
	}
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	/**
	 * @return
	 */
	public String execute() {
		//将电影id放入session中
		Map session=ActionContext.getContext().getSession();
		if(session.containsKey("movieID"))
			session.remove("movieID");
		session.put("movieID", movieID);
		// TODO Auto-generated method stub
		Movie movie = movieDAO.getMovieDetailsByMovieID(movieID);
		//System.out.println("movieID:  "+movieID);

		ceph.init();
		
		List<String>imageUrls = new ArrayList<String>();
		List<MovieImage>mis = movieImageDAO.getMovieImageByMovieID(movieID);
		for(int i=0; i<mis.size(); i++){
			String str = mis.get(i).getImageName();
			Bucket bucketimage = ceph.getBucketByName("lpshouimage");
			String imageUrl = ceph.genarateUrlInBucketForObject(bucketimage, str).toString();
			imageUrls.add(imageUrl);
		}
		
		
		String vedioUrl1 = movie.getVedioUrl();
		Bucket bucketfilm = ceph.getBucketByName("lpshoufilm");
		String vedioUrl = ceph.genarateUrlInBucketForObject(bucketfilm, vedioUrl1).toString();
		System.out.println("vedio:"+vedioUrl);
	
		if(movie!=null){
			Map request = (Map)ActionContext.getContext().get("request");
			request.put("movieDetails", movie);
			request.put("vedioUrl", vedioUrl);
			request.put("imageUrls", imageUrls);
			return SUCCESS;
		}
		return ERROR;
	}
}