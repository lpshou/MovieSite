package com.movie.export;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.movie.dao.movieDAO;
import com.movie.domain.Movie;

public class movieExport {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		LineNumberReader lineReader = new LineNumberReader(new FileReader(
				"/root/workspace/MovieSite/src/com/ibm/taste/example/movie/utils/movies.dat"));
		String line = "";
		
		PrintWriter out = new PrintWriter("/ttserver/test.dat");
		while ((line = lineReader.readLine()) != null) {
			out.println(line);
		}
		/*movieDAO md = new movieDAO();
		List<Movie>movies = new ArrayList<Movie>();
		movies=md.getAllMovies();
		for(Movie movie:movies){
			System.out.print(movie.getId()+"\t"+movie.getName()+"\t"+movie.getPublished_year());
		}*/
	}

}
