package com.green.plate.greenplateapi.controller;

import com.green.plate.greenplateapi.service.file.impl.FileServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/file")
public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
    private final FileServiceImpl fileService;

    public FileController(FileServiceImpl fileService) {
        this.fileService = fileService;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public List<String> addFile(@RequestParam("files") MultipartFile[] files) {
        LOGGER.debug("Adicionar Arquivo Chamado");
        return fileService.addSingleFile(files);
    }
}
