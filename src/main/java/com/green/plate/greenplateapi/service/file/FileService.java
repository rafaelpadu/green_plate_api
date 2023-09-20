package com.green.plate.greenplateapi.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    List<String> addSingleFile(MultipartFile[] files);
}
