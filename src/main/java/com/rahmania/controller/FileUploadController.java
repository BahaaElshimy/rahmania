package com.rahmania.controller;

import com.rahmania.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bahaa on 08/02/18.
 */

@RestController
@RequestMapping("api/")
public class FileUploadController {
    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);


    @Autowired
    StorageService storageService;

    List<String> files = new ArrayList<>();

    // Multiple file upload
  /*  @PostMapping("/uploadfile")
    public String uploadFileMulti(@RequestParam("uploadfile") MultipartFile file , HttpServletRequest request) throws Exception {
        try {
            storageService.store(file , subject.getId(), request);
            files.add(file.getOriginalFilename());
            return "You successfully uploaded - " + file.getOriginalFilename();
        } catch (Exception e) {
            throw new Exception("FAIL! Maybe You had uploaded the file before or the file's size > 500KB");
        }
    }*/

    @GetMapping("/getallfiles")
    public List<String> getListFiles() {List<String> lstFiles = new ArrayList<String>();
        try{
            lstFiles = files.stream()
                    .map(fileName -> MvcUriComponentsBuilder
                            .fromMethodName(FileUploadController.class, "getFile", fileName).build().toString())
                    .collect(Collectors.toList());
        }catch(Exception e){
            throw e;
        }

        return lstFiles;
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}