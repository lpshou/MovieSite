package com.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.movie.domain.Movie;

public class movieDAO {
	public final static String TABLE_NAME = "movies";
	public final static String ID_COLUMN = "id";
	public final static String NAME_COLUMN = "name";
	public final static String PUBLISHED_YEAR_COLUMN = "published_year";
	public final static String TYPE_COLUMN = "type";
	public final static String DETAILS_COLUMN = "details";
	public final static String VEDIOURL_COLUMN = "vedioUrl";
	public final static String MINIIMAGEURL_COLUMN = "miniImageUrl";

	//功能：插入一部电影
	//测试通过..
	public static void insertMovie(Movie movie) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into " + TABLE_NAME + " ( " 
				+ NAME_COLUMN + ", " + PUBLISHED_YEAR_COLUMN + ", "
				+ TYPE_COLUMN + ", "+DETAILS_COLUMN+", " 
				+ VEDIOURL_COLUMN+", "
				+MINIIMAGEURL_COLUMN + 
				") values (?, ?, ?, ?, ?, ?)";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
				ps.setString(1, movie.getName());
				ps.setString(2, movie.getPublished_year());
				ps.setString(3, movie.getType());
				ps.setString(4, movie.getDetails());
				ps.setString(5, movie.getVedioUrl());
				ps.setString(6, movie.getMiniImageUrl());
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

	//功能：插入一组电影
	//测试通过，
	public static void insertMovies(List<Movie> movies) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into " + TABLE_NAME + " ( " 
				+ NAME_COLUMN + ", " + PUBLISHED_YEAR_COLUMN + ", "
				+ TYPE_COLUMN + ", " + DETAILS_COLUMN+", "
				+VEDIOURL_COLUMN+", "
				+MINIIMAGEURL_COLUMN
				+") values (?, ?, ?, ?, ?, ?)";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			for (Movie movie : movies) {
				ps.setString(1, movie.getName());
				ps.setString(2, movie.getPublished_year());
				ps.setString(3, movie.getType());
				ps.setString(4, movie.getDetails());
				ps.setString(5, movie.getVedioUrl());
				ps.setString(6, movie.getMiniImageUrl());
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

	public static Movie constructMovieFromResultSet(ResultSet rs) {
		try {
			Movie movie = new Movie();
			movie.setId(rs.getInt(ID_COLUMN));
			movie.setName(rs.getString(NAME_COLUMN));
			movie.setPublished_year(rs.getString(PUBLISHED_YEAR_COLUMN));
			movie.setType(rs.getString(TYPE_COLUMN));
			movie.setDetails(rs.getString(DETAILS_COLUMN));
			movie.setVedioUrl(rs.getString(VEDIOURL_COLUMN));
			movie.setMiniImageUrl(rs.getString(MINIIMAGEURL_COLUMN));
			return movie;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//功能：通过电影id获得电影信息
	//测试通过
	public static List<Movie> getMovies(Collection<String> movieIDs) {
		List<Movie> movies = new ArrayList<Movie>();
		String movieIDString = StringUtil.connectString(movieIDs, ", ");

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN
				+ " IN ( " + movieIDString + " )";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnectionFromDataSource();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Movie movie = constructMovieFromResultSet(rs);
				if (movie != null) {
					movies.add(movie);
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
		return movies;
	}

	public static Map<String, Movie> getMovieMap(Collection<String> movieIDs) {
		Map<String, Movie> movies = new HashMap<String, Movie>();
		String movieIDString = StringUtil.connectString(movieIDs, ", ");

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN
				+ " IN ( " + movieIDString + " )";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnectionFromDataSource();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Movie movie = constructMovieFromResultSet(rs);
				if (movie != null) {
					movies.put(String.valueOf(movie.getId()), movie);
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
		return movies;
	}

	public static List<Movie> getAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();

		String sql = "SELECT * FROM " + TABLE_NAME;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Movie movie = constructMovieFromResultSet(rs);
				if (movie != null) {
					movies.add(movie);
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
		return movies;
	}

	//通过电影名中的关键字获得movie信息
	public static List<Movie> getMovieByMovieKeyword(String keyword){
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_COLUMN
		+ " LIKE " +"'%" + keyword+ "%' ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnectionFromDataSource();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Movie movie = constructMovieFromResultSet(rs);
				if (movie != null) {
					movies.add(movie);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return movies;
	}
	//通过id获取moive的部分信息
	public static Movie getMovieByMovieID(String movieID) {

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN
				+ " =  " + movieID + " ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnectionFromDataSource();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Movie movie = constructMovieFromResultSet(rs);
				return movie;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	//获取movie的全部信息，，，，
	public static Movie getMovieDetailsByMovieID(String movieID) {

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN
				+ " =  " + movieID + " ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnectionFromDataSource();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Movie movie = new Movie();
				movie.setId(rs.getInt(ID_COLUMN));
				movie.setName(rs.getString(NAME_COLUMN));
				movie.setPublished_year(rs.getString(PUBLISHED_YEAR_COLUMN));
				movie.setType(rs.getString(TYPE_COLUMN));
				movie.setVedioUrl(rs.getString("vedioUrl"));
				movie.setDetails(rs.getString("details"));
				movie.setMiniImageUrl(rs.getString("miniImageUrl"));
				return movie;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
