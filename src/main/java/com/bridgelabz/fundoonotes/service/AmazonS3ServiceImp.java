package com.bridgelabz.fundoonotes.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bridgelabz.fundoonotes.Exceptions.UserExceptions;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utilis.JWToperations;

@Service
public class AmazonS3ServiceImp implements AmazonS3Service {
	private String awsS3AudioBucket;
	private AmazonS3 amazonS3;
	@Autowired
	private JWToperations ope;
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private Environment env;

	@Autowired
	public AmazonS3ServiceImp(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider,
			String awsS3AudioBucket) {

		this.amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(awsCredentialsProvider)
				.withRegion(awsRegion.getName()).build();
		this.awsS3AudioBucket = awsS3AudioBucket;
	}

	@Async
	public void uploadFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess, String token) {
		Long id = ope.parseJWT(token);
		User user = userrepository.get(id).orElseThrow(() -> new UserExceptions(404, env.getProperty("notexist")));

		String fileName = multipartFile.getOriginalFilename();
		user.setProfile(fileName);
		try {
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multipartFile.getBytes());
			fos.close();

			PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3AudioBucket, fileName, file);

			if (enablePublicReadAccess) {
				putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
			}
			this.amazonS3.putObject(putObjectRequest);
			file.delete();
			userrepository.save(user);
		} catch (IOException ex) {
			System.out.println("there is some error");
		}
	}

	@Async
	public void deleteFileFromS3Bucket(String fileName, String token) {
		Long id = ope.parseJWT(token);
		User user = userrepository.get(id).orElseThrow(() -> new UserExceptions(404, env.getProperty("notexist")));
		user.setProfile("null");
		amazonS3.deleteObject(new DeleteObjectRequest(awsS3AudioBucket, fileName));
		userrepository.save(user);

	}

}
