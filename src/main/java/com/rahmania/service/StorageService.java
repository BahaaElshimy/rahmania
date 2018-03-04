package com.rahmania.service;

/**
 * Created by bahaa on 08/02/18.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class StorageService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("/resources/video/");

    public String store(MultipartFile file, Long id, HttpServletRequest request) {
        if (file.isEmpty())
            return null;
        try {
            byte[] bytes = file.getBytes();
            String realPath = request.getServletContext().getRealPath("/resources/video");

            File dir = new File(realPath + "/" + id + "");
            if (dir.exists()) {
                FileSystemUtils.deleteRecursively(dir);
            }
            dir.mkdir();


            Path path = Paths.get(dir + "/" + file.getOriginalFilename());
            Files.write(path, bytes);
            log.info("*********** Path : " + path + "*******************");
            return path.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteFile(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            FileSystemUtils.deleteRecursively(dir);
        }
    }

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    public String moveFile(String videoPath, Long id) {
        Path file = rootLocation.resolve(videoPath);


        return null;
    }
}