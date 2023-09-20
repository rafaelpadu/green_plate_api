package com.green.plate.greenplateapi.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Identity;
import com.google.cloud.Policy;
import com.google.cloud.storage.*;
import com.green.plate.greenplateapi.exception.BadRequestException;
import com.green.plate.greenplateapi.exception.FileWriteException;
import com.green.plate.greenplateapi.exception.GCPFileUploadException;
import com.green.plate.greenplateapi.exception.InvalidFileTypeException;
import org.apache.commons.io.FileUtils;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataBucketUtil {

    @Value("${gcp.config.file}")
    private String gcpConfigFile;
    @Value("${gcp.project.id}")
    private String gcpProjectId;

    @Value("${gcp.bucket.id}")
    private String gcpBucketId;

    @Value("${gcp.dir.name}")
    private String gcpDirectoryName;

    public String uploadFile(MultipartFile multipartFile, String fileName, String contentType){
        try{
            byte[] fileData = FileUtils.readFileToByteArray(convertFile(multipartFile));
            InputStream inputStream = new ClassPathResource(gcpConfigFile).getInputStream();
            StorageOptions options = StorageOptions.newBuilder().setProjectId(gcpProjectId).setCredentials(GoogleCredentials.fromStream(inputStream)).build();
            Storage storage = options.getService();
            Policy originalPolicy = storage.getIamPolicy(gcpBucketId);
            storage.setIamPolicy(gcpBucketId, originalPolicy.toBuilder().addIdentity(StorageRoles.objectViewer(), Identity.allUsers()).build());
            Bucket bucket = storage.get(gcpBucketId, Storage.BucketGetOption.fields());
            RandomString id = new RandomString(6, ThreadLocalRandom.current());
            Blob blob = bucket.create(gcpDirectoryName + "/" + fileName + "-" + id.nextString() + checkFileExtension(fileName), fileData, contentType);
            if(blob != null){
                return blob.getMediaLink();
            }
        }catch (Exception e){
            throw new GCPFileUploadException("Um erro ocorreu enquanto armazenavamos dados no GCS");
        }
        throw new GCPFileUploadException("Um erro ocorreu enquanto armazenavamos dados no GCS");
    }
    private File convertFile(MultipartFile file) {

        try{
            if(file.getOriginalFilename() == null){
                throw new BadRequestException("Original file name is null");
            }
            File convertedFile = new File(file.getOriginalFilename());
            FileOutputStream outputStream = new FileOutputStream(convertedFile);
            outputStream.write(file.getBytes());
            outputStream.close();
            return convertedFile;
        }catch (Exception e){
            throw new FileWriteException("An error has occurred while converting the file");
        }
    }

    private String checkFileExtension(String fileName) {
        if(fileName != null && fileName.contains(".")){
            String[] extensionList = {".png", ".jpeg", ".pdf", ".doc", ".mp3"};

            for(String extension: extensionList) {
                if (fileName.endsWith(extension)) {
                    return extension;
                }
            }
        }
        throw new InvalidFileTypeException("Not a permitted file type");
    }
}
