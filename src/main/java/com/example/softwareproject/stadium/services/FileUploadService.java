package com.example.softwareproject.stadium.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
    public void saveFile(String uploadDir,String fileName,MultipartFile multipartFile) throws IOException{
        Path currentAbsoulutePath = Paths.get(".").toAbsolutePath();
        Path uploadPath = Paths.get(currentAbsoulutePath + "/src/main/resources/static/photos/" + uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            throw new IOException("couldn't Save File" + fileName,e);
        }
    }
}
