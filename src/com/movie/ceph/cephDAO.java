package com.movie.ceph;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.movie.dao.DBUtil;
import com.movie.dao.movieImageDAO;
import com.movie.domain.Movie;
import com.movie.domain.MovieImage;

public class cephDAO {  //用于上传图片，电影，并将产生的urls导入数据库中
	private static AmazonS3 conn;
	// 要导入的视频，图片个数num
	private static int num = 10;
	
	//

	// 添加一个bucket
	public void addBucket(String name){
		 ceph.createBucket(name);
		 ceph.listAllBuckets();
	}
	// 删除一个bucket
	public void delBucket(String name){
		  ceph.delBucket(name);
		  ceph.listAllBuckets();
	}
	// 获取名为name的bucket
	public static Bucket getBucket(String name){
		 Bucket bucket = ceph.getBucketByName(name);
		 System.out.println("获取bucket： "+bucket.getName()); 
		 return bucket;
	}
	// 列出某一bucket下的所有objects
	public static void listObjectInBucket(String name){
		 System.out.println("----------------------------------------------------");
		 System.out.println("bucket : "+name+"下的objects有： ");
		 Bucket bucket = ceph.getBucketByName(name);
		 ceph.listObjectInBucket(bucket);
	}
	// 列出某一名为name的bucket下有prefix前缀的所有objects
	public static void listObjectInBucketWithPrefix(String name, String prefix){
		 System.out.println("----------------------------------------------------");
		 System.out.println("bucket : "+name+"下的objects有： ");
		 ceph.init();
		 Bucket bucket = ceph.getBucketByName(name);
		 ceph.listObjectInBucketWithPrefix(bucket, prefix);
	}
	//在bucket下添加object
	public void addObjectToBucket(String bucketName, String name, File f){
		Bucket bucket = ceph.getBucketByName(bucketName);
		ceph.putObjectInBucket(bucket, name, f);
	}
	//从bucket下下载object放到path下，名字为name
	public static void getObjectFromBucket(String bucketName, String path, String objectName){ 
		  Bucket bucket = ceph.getBucketByName(bucketName);
		  ceph.getObjectFromBucket(bucket, path, objectName); 	 
	}
	//在bucket下删除object
	public static void delObjectFromBucket(String bucketName, String objectName){
		 Bucket bucket = ceph.getBucketByName(bucketName);
		 ceph.delObjectFromBucket(bucket,objectName); 

	}
	

	// 将图片upload到名为bucketName的bucket中
	public static void importImageObjectsToBucket(String bucketName) {
		Bucket bucket = ceph.getBucketByName(bucketName);
		ceph.init();
		//j表示第一级目录，i表示文件名
		for(int j=1;j<11;j++){
		for (int i = 1; i < num + 1; i++) {
			File f = new File("/home/zforCeph/upload/"+j+"/" + i + ".jpg");
			String name = "image"+j+"/" + i + ".jpg";
			ceph.putObjectInBucket(bucket, name, f);
		}
		}
	}
	
	// 将电影信息upload到名为lpshou的bucket中
	public static void importVedioObjectsToBucket(Bucket bucket) {
		ceph.init();
		for (int i = 1; i < num + 1; i++) {
			File f = new File("/home/zforCeph/upload/" + i + ".mkv");
			String name = "film" + i + ".mkv";
			ceph.putObjectInBucket(bucket, name, f);
		}
	}
	
	//上传图片文件到名为bucketName的bucket中，
	//在UpLoadMovie.java中调用
	public static void uploadImage(File file, String fileName, String bucketName){
		ceph.init();
		Bucket bucket = ceph.getBucketByName(bucketName);
		ceph.putObjectInBucket(bucket, fileName, file);
	}
	//上传电影到到名为bucketName的bucket中，
	//在UpLoadMovie.java中调用
	public static void upLoadMovie(File file,String fileName, String bucketName){
		ceph.init();
		Bucket bucket = ceph.getBucketByName(bucketName);
		ceph.putObjectInBucket(bucket, fileName, file);
	}
	
	//将图片信息批量载入数据库
	public static void imagesToDataBase(){
		List<MovieImage>mis = new ArrayList<MovieImage>();
		
		for(int i=1 ;i<1700; i++){
				//mi.setMovieID(Integer.valueOf(i));
				int j=i%10;
				j+=1;
					for(int k=1;k<=10;k++){
						String str="";
						if(j!=11){
							str="image"+j+"/"+k+".jpg";
						}else{
							str="image"+1+"/"+k+".jpg";
						}
						MovieImage mi = new MovieImage();
						mi.setImageName(str);
						mi.setMovieID(Integer.valueOf(i));
						mi.setImageDetails("test");
						mis.add(mi);
					}

		}
		movieImageDAO.insertImages(mis);
	}
	

}
