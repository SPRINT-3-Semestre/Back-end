package com.example.EditMatch.controller;

import com.example.EditMatch.s3.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private S3Services s3Services;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        s3Services.uploadFile(fileName, file);
        return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded successfully: " + fileName);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        byte[] fileBytes = s3Services.downloadFile(fileName);
        return ResponseEntity.ok().body(fileBytes);
    }
}
