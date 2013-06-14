package com.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.movie.domain.MovieImage;

public class movieImageDAO {
	public final static String TABLE_NAME = "movie_image";
	public final static String MOVIEID_COLUMN = "movieID";
	public final static String IMAGEID_COLUMN = "imageID";
	public final static String IMAGENAME_COLUMN = "imageName";
	public final static String IMAGEDETAILS_COLUMN = "imageDetails";
	
	//功能：插入一张图片
	//测试通过..
	public static void insertImage(MovieImage image) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into " + TABLE_NAME + " ( " 
				+ MOVIEID_COLUMN + ", "
				+ IMAGENAME_COLUMN + ", "+ IMAGEDETAILS_COLUMN +
				") values (?, ?, ?)";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
				ps.setInt(1, image.getMovieID());
				ps.setString(2, image.getImageName());
				ps.setString(3, image.getImageDetails());
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
	
	//功能：插入一组图片
	//测试通过
	public static void insertImages(List<MovieImage> images) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into " + TABLE_NAME + " ( " 
					+ MOVIEID_COLUMN + ", " 
					+ IMAGENAME_COLUMN + ", "+ IMAGEDETAILS_COLUMN +
					") values (?, ?, ?)";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			for (MovieImage image : images) {
				ps.setInt(1, image.getMovieID());
				ps.setString(2, image.getImageName());
				ps.setString(3, image.getImageDetails());
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
	
	//根据电影id取出所有的图片
	//测试通过...
	public static List<MovieImage> getMovieImageByMovieID(String movieID){
		List<MovieImage> movieImages = new ArrayList<MovieImage>();
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + MOVIEID_COLUMN
				+ " =  " + movieID + " ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnectionFromDataSource();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MovieImage movieImage = new MovieImage();
				movieImage.setMovieID(rs.getInt(MOVIEID_COLUMN));
				movieImage.setImageID(rs.getInt(IMAGEID_COLUMN));
				movieImage.setImageName(rs.getString(IMAGENAME_COLUMN));
				movieImage.setImageDetails(rs.getString(IMAGEDETAILS_COLUMN));
				if (movieImage != null) {
					movieImages.add(movieImage);
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
		return movieImages;
	}
	//获取所有的电影图片信息
	//根据电影id取出所有的图片
	//测试通过...
	public static List<MovieImage> getAllMovieImage(){
		List<MovieImage> movieImages = new ArrayList<MovieImage>();
		String sql = "SELECT * FROM " + TABLE_NAME ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnectionFromDataSource();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MovieImage movieImage = new MovieImage();
				movieImage.setMovieID(rs.getInt(MOVIEID_COLUMN));
				movieImage.setImageID(rs.getInt(IMAGEID_COLUMN));
				movieImage.setImageName(rs.getString(IMAGENAME_COLUMN));
				movieImage.setImageDetails(rs.getString(IMAGEDETAILS_COLUMN));
				if (movieImage != null) {
					movieImages.add(movieImage);
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
		return movieImages;
	}
}
