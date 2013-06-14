package com.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.struts2.components.Date;

import com.movie.domain.Movie;
import com.movie.domain.MovieList;
import com.movie.domain.MoviePreference;

public class moviePreferenceDAO {
	public final static String TABLE_NAME = "movie_preferences";
	public final static String USER_ID_COLUMN = "userID";
	public final static String MOVIE_ID_COLUMN = "movieID";
	public final static String RATING = "preference";
	public final static String TIMESTAMP = "timestamp";

	//查询
	//测试通过...
	public static MoviePreference getMoviePreference(int userID, int movieID){

		String sql = "SELECT * FROM " + TABLE_NAME 
		 + " WHERE " + MOVIE_ID_COLUMN + " = " + movieID
		 + " AND "+ USER_ID_COLUMN + " =  " + userID;
		Connection conn = null;
		PreparedStatement ps = null;
		MoviePreference mp = new MoviePreference();
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnectionFromDataSource();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				mp.setMovieID(rs.getInt("movieID"));
				mp.setUserID(rs.getInt("userID"));
				mp.setPreference(rs.getInt("preference"));
				mp.setTimestamp(rs.getInt("timestamp"));
			}
			return mp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//插入
	//测试通过...
	public static void insertRating(int userID, int movieID, int preference){
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into " + TABLE_NAME + " ( " + USER_ID_COLUMN
				+ ", " + MOVIE_ID_COLUMN + ", " + RATING + ", " + TIMESTAMP
				+ ") values (?, ?, ?, ?)";
		try {
			conn.setAutoCommit(false);
			long l = System.currentTimeMillis()/1000;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(userID));
			ps.setInt(2, Integer.valueOf(movieID));
			ps.setInt(3, Integer.valueOf(preference));
			ps.setInt(4, Integer.parseInt(l+""));//时间值统一设置为当前时间的秒数表示
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
	
	//更新
	//测试通过...
	public static void updateRating(int userID, int movieID, int preference){
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		//String sql = "update movie_preferences set preference = ? , timestamp=? where userID=? AND movieID=?";
		String sql = "update "+ TABLE_NAME +" set "+ RATING +" = ? ," + TIMESTAMP +" = ? where " + USER_ID_COLUMN
		+ " = ? AND "+ MOVIE_ID_COLUMN + " = ? ";
		try {
			conn.setAutoCommit(false);
			long l = System.currentTimeMillis()/1000;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(preference));
			ps.setInt(2, Integer.parseInt(l+""));//时间值统一设置为当前时间的秒数表示
			ps.setInt(3, Integer.valueOf(userID));
			ps.setInt(4, Integer.valueOf(movieID));	
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
	
	//插入或者更新
	//测试通过..（有改进空间）
	public static void updateOrInsertRating(int userID, int movieID, int preference){
		MoviePreference mp = getMoviePreference(userID, movieID);
		if(mp!=null)
			updateRating(userID, movieID, preference);
		else
			insertRating(userID, movieID, preference);
		
	}
	
	public static void insertRating(MoviePreference mp) {
	}

	public static void insertRatings(List<MoviePreference> mps) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into " + TABLE_NAME + " ( " + USER_ID_COLUMN
				+ ", " + MOVIE_ID_COLUMN + ", " + RATING + ", " + TIMESTAMP
				+ ") values (?, ?, ?, ?)";
		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			for (MoviePreference mp : mps) {
				ps.setInt(1, mp.getUserID());
				ps.setInt(2, mp.getMovieID());
				ps.setInt(3, mp.getPreference());
				ps.setInt(4, mp.getPreference());
				ps.addBatch();
			}

			ps.executeBatch();
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

	//获取所有的评分信息
	public static List<MoviePreference> getAllMoviePreferences() {
		List<MoviePreference> mps = new ArrayList<MoviePreference>();

		String sql = "SELECT * FROM " + TABLE_NAME;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MoviePreference mp = new MoviePreference();
				mp.setMovieID(rs.getInt("movieID"));
				mp.setUserID(rs.getInt("userID"));
				mp.setPreference(rs.getInt("preference"));
				mp.setTimestamp(rs.getInt("timestamp"));
				if (mp != null) {
					mps.add(mp);
				}
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
		return mps;
	}

	//查询获取评分大于4.5的电影
	public static MovieList getMoviesByPreference() {

		String sql = "SELECT * FROM " + TABLE_NAME +" mp, "
		+ movieDAO.TABLE_NAME +" m"+ " where "+"mp."
		+ MOVIE_ID_COLUMN + " = m."+ movieDAO.ID_COLUMN+
		" group by "+ MOVIE_ID_COLUMN +
		" having avg(" + RATING +" ) > 4.5 ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MovieList movies = new MovieList();
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
	public static MovieList getMoviesByUserID(String userID) {

		String sql = "SELECT * FROM " + TABLE_NAME + " mp, "
				+ movieDAO.TABLE_NAME + " m" + " WHERE " + "mp."
				+ MOVIE_ID_COLUMN + " = m." + movieDAO.ID_COLUMN + " AND "
				+ "mp." + USER_ID_COLUMN + " =  " + userID + " ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MovieList movies = new MovieList();
		try {
			conn = DBUtil.getConnectionFromDataSource();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// conn = DBUtil.getConnection();
			// pstmt = conn.prepareStatement(sql);
			// rs = pstmt.executeQuery();
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

	private static void constructMoviesFromResultSet(ResultSet rs,
			MovieList movies) {
		try {
			Movie movie = new Movie();
			movie.setMiniImageUrl(rs.getString(movieDAO.MINIIMAGEURL_COLUMN));
			movie.setId(rs.getInt(movieDAO.ID_COLUMN));
			movie.setName(rs.getString(movieDAO.NAME_COLUMN));
			movie.setPublished_year(rs.getString(movieDAO.PUBLISHED_YEAR_COLUMN));
			movie.setType(rs.getString(movieDAO.TYPE_COLUMN));
			Float score = rs.getFloat(RATING);
			movies.add(movie, score);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
