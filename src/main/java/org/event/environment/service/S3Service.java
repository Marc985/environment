package org.event.environment.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
public class S3Service {
    private final AmazonS3 s3client;


    @Value("${aws.s3.bucket}")
    private String bucketName;
    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }


    public String uploadFile(String keyName, MultipartFile file) throws IOException {
        var putObjectResult = s3client.putObject(bucketName, keyName, file.getInputStream(), null);
        var appUrl=System.getenv("APP_URL");
        return appUrl+"/view/"+keyName;


    }

    public S3Object getFile(String keyName) {
        S3Object s3Object=s3client.getObject(bucketName, keyName);
        return s3Object;
    }
    public void deleteFile(String keyName){
        s3client.deleteObject(new DeleteObjectRequest(bucketName,keyName));

    }
}
