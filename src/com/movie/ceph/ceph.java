package com.movie.ceph;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.StringUtils;

public class ceph {
	private static AmazonS3 conn;
	// 初始化
	public static AmazonS3 init() {
		String accessKey = "R3949EV1BHELWIZXKGJR";
		String secretKey = "fmimw6JPDApNC5r+iYknX7JXsvBu8DON3J24Sk1A";
		AWSCredentials credentials = new BasicAWSCredentials(accessKey,
				secretKey);
		conn = new AmazonS3Client(credentials);
		conn.setEndpoint("http://192.168.0.19");
		return conn;
	}

	// 创建新的bucket
	public static Bucket createBucket(String name) {
		Bucket bucket = conn.createBucket(name);
		return bucket;
	}

	// 删除一个bucket
	public static void delBucket(String name) {
		conn.deleteBucket(name);
	}

	// 获取名为name的bucket
	public static Bucket getBucketByName(String name) {
		List<Bucket> buckets = conn.listBuckets();
		for (Bucket bucket : buckets) {
			if (bucket.getName().equalsIgnoreCase(name))
				return bucket;
		}
		return null;
	}

	// 列出所有buckets
	public static void listAllBuckets() {
		List<Bucket> buckets = conn.listBuckets();
		for (Bucket bucket : buckets) {
			System.out.println(bucket.getName() + "\t\t"
					+ StringUtils.fromDate(bucket.getCreationDate()));
		}
	}

	// 列出某个bucket的所有objects
	public static void listObjectInBucket(Bucket bucket) {
		List<Bucket> buckets = conn.listBuckets();
		ObjectListing objects = conn.listObjects(bucket.getName());
		do {
			for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
				System.out.println(objectSummary.getKey()+ "\t"
						+ objectSummary.getSize()+ "\t"
						+ StringUtils.fromDate(objectSummary.getLastModified()));
			}
			objects = conn.listNextBatchOfObjects(objects);
		} while (objects.isTruncated());
	} 
	

	//列出有prefix前缀的所有objects
	public static void listObjectInBucketWithPrefix(Bucket bucket, String prefix){
			ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
				.withBucketName(bucket.getName())
				.withPrefix(prefix);
			ObjectListing objects = conn.listObjects(listObjectsRequest);
			do {
				for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
					System.out.println(objectSummary.getKey()+ "\t"
							+ objectSummary.getSize()+ "\t"
							+ StringUtils.fromDate(objectSummary.getLastModified()));
				}
				objects = conn.listNextBatchOfObjects(objects);
			} while (objects.isTruncated());		
	}

	//将文件f放到bucket中，名为name； 权限为公共读写。
	public static void putObjectInBucket(Bucket bucket, String name, File f) {

		conn.putObject(bucket.getName(), name, f);
		conn.setObjectAcl(bucket.getName(), name,
				CannedAccessControlList.PublicReadWrite);
	}

	//从bucket中下载object name，并放在"/home/zforCeph/download"文件夹下
	public static void getObjectFromBucket(Bucket bucket, String path, String name) {
		conn.getObject(new GetObjectRequest(bucket.getName(), name), new File(
				path+name));
	}

	//从bucket中删除名为name的object
	public static void delObjectFromBucket(Bucket bucket, String name) {
		conn.deleteObject(bucket.getName(), name);
	}

	//获取bucket中名为name的object的下载地址
	public static URL genarateUrlInBucketForObject(Bucket bucket, String name) {
		GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(
				bucket.getName(), name);
		//System.out.println(conn.generatePresignedUrl(request));
		return conn.generatePresignedUrl(request);
	}

	//ubuntu下在java中执行command命令
	public static void executeCommand(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			InputStreamReader ir = new InputStreamReader(process
					.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}