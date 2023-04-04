package com.example.recommendations.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtil {

    public static String saveFile(String fileName, MultipartFile file) throws IOException {
        // Create a directory for storing the files if it does not exist
        File directory = new File("upload");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File newFile = new File(directory.getAbsolutePath() + File.separator + fileName);
        file.transferTo(newFile);

        return directory.getAbsolutePath();
    }
}