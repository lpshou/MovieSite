package com.movie.ceph;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.util.StringUtils;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.movie.dao.DBUtil;
import com.movie.dao.movieDAO;
import com.movie.domain.Movie;

public class cephApiCall {

	public static void main(String[] args) {
		ceph.init();
		ceph.listAllBuckets();
		//cephDAO.listObjectInBucket("lpshouimage");
		//ceph.listObjectInBucket(bucket);
		//cephDAO.imagesToDataBase();

		
	}
}
