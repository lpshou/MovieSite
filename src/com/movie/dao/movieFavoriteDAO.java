package com.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.movie.domain.Movie;
import com.movie.domain.MovieFavorite;
import com.movie.domain.MovieList;
import com.movie.domain.MoviePreference;

public class movieFavoriteDAO {
	public final static String TABLE_NAME = "movie_favorites";
	public final static String USER_ID_COLUMN = "userID";
	public final static String MOVIE_ID_COLUMN = "movieID";
	public final static String FAVORITE_COLUMN = "favorite";
	
	//判断movie_favorites中是否存在用户userID的movieID收藏
	//测试通过...
	public static Boolean hasMovieFavorite(int userID, int movieID){

		String sql = "SELECT * FROM " + TABLE_NAME 
		 + " WHERE " + MOVIE_ID_COLUMN + " = ? " 
		 + " AND "+ USER_ID_COLUMN + " =  ? ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnectionFromDataSource();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(movieID));
			ps.setInt(2, Integer.valueOf(userID));
			Boolean b = false;
			rs = ps.executeQuery();
			if(rs.next()!=false){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;	
	}

	//插入
	//测试通过...
	public static void insertMovieFavorite(int userID, int movieID){
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into " + TABLE_NAME + " ( " + USER_ID_COLUMN
				+ ", " + MOVIE_ID_COLUMN + ", " + FAVORITE_COLUMN 
				+ ") values (?, ?, ?)";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(userID));
			ps.setInt(2, Integer.valueOf(movieID));
			ps.setBoolean(3, true);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//删除
	//测试通过...
	public static void deleteMovieFavorite(int userID, int movieID){
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "delete  from " + TABLE_NAME + " where " + USER_ID_COLUMN
				+ " = ? and "+ MOVIE_ID_COLUMN + " = ? ";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(userID));
			ps.setInt(2, Integer.valueOf(movieID));
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//如果没有收藏则添加到收藏
	public static Boolean addToMovieFavorite(int userID, int movieID){
		Boolean b = hasMovieFavorite(userID, movieID);
		if(b==true){}
		else{
			insertMovieFavorite(userID, movieID);
		}
		return true;
	}

	//通过rs构造List<Movie>
	private static void constructMoviesFromResultSet(ResultSet rs,
			List<Movie> movies) {
		try {
			Movie movie = new Movie();
			movie.setMiniImageUrl(rs.getString(movieDAO.MINIIMAGEURL_COLUMN));
			movie.setId(rs.getInt(movieDAO.ID_COLUMN));
			movie.setName(rs.getString(movieDAO.NAME_COLUMN));
			movie.setPublished_year(rs.getString(movieDAO.PUBLISHED_YEAR_COLUMN));
			movie.setType(rs.getString(movieDAO.TYPE_COLUMN));
			movies.add(movie);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//通过userID获得用户收藏的电影信息
	public static List<Movie> getMoviesByUserID(int userID){
		String sql = "SELECT * FROM " + TABLE_NAME + " mp, "
		+ movieDAO.TABLE_NAME + " m" + " WHERE " + "mp."
		+ MOVIE_ID_COLUMN + " = m." + movieDAO.ID_COLUMN + " AND "
		+ "mp." + USER_ID_COLUMN + " =  " + userID + " ";


		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Movie>movies = new ArrayList<Movie>();
		try {
			conn = DBUtil.getConnectionFromDataSource();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				constructMoviesFromResultSet(rs, movies);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return movies;
		}
	}
