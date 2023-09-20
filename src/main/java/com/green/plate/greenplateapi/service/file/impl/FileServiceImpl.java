package com.green.plate.greenplateapi.service.file.impl;

import com.google.api.gax.rpc.NotFoundException;
import com.green.plate.greenplateapi.exception.GCPFileUploadException;
import com.green.plate.greenplateapi.exception.ResourceNotFoundException;
import com.green.plate.greenplateapi.service.file.FileService;
import com.green.plate.greenplateapi.utils.DataBucketUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    private final DataBucketUtil dataBucketUtil;

    public FileServiceImpl(DataBucketUtil dataBucketUtil) {
        this.dataBucketUtil = dataBucketUtil;
    }

    @Override
    public List<String> addSingleFile(MultipartFile[] files) {
        List<String> urlList = new ArrayList<>();
        for (MultipartFile file : files) {
            String originalFileName = file.getOriginalFilename();
            if(originalFileName == null){
                throw new ResourceNotFoundException("Nome do arquivo original é nulo");
            }
            Path path = new File(originalFileName).toPath();
            try{
                String contentType = Files.probeContentType(path);
                String fileUrl = dataBucketUtil.uploadFile(file, originalFileName, contentType);
                if(fileUrl != null){
                    urlList.add(fileUrl);
                }
            }catch (Exception e){
                throw new GCPFileUploadException("Erro ocorreu enquanto imagem era salva");
            }
        }
        return urlList;
    }
}
