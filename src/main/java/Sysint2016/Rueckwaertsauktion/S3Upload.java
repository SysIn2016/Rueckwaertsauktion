package Sysint2016.Rueckwaertsauktion;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Upload {
//	String uploadFileName;
	/*
    public  UploadAmazoneObjekte(String uploadFileName){
    this.uploadFileName = uploadFileName;
    } */
   
	private static String bucketName     = "ruckwaertsaution";
	//private static String keyName        = "Object2";
	//private static String uploadFileName = "C:\\Users\\carole\\Desktop\\Ausarbeit_carole2\\test.bmp";
//	private static String uploadFileName = "test.docx";
	
	public static void bildSpeichern(String keyName, File file ) {
		File credentials1 = new File("src/main/Webseite/Credentials.txt");
		String line="";
		try {
		BufferedReader br = new BufferedReader(new FileReader(credentials1));
			line = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//public void sauvegarde(){
     //   AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
	     AWSCredentials credentials = new BasicAWSCredentials(line.split(",")[0], line.split(",")[1]);
		 AmazonS3 s3client = new AmazonS3Client(credentials);
        try {
            System.out.println("Uploading a new object to S3 from a file\n");
          //  File file = new File(uploadFileName);
            s3client.putObject(new PutObjectRequest(
            		                 bucketName, keyName, file));

         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
}
