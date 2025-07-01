package com.server.controllers;

import com.server.Utils.pptUploader;
import com.server.model.slideDataModel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
// import java.io.IOException;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


@RestController
@RequestMapping("api/ppt")

public class pptController{

    @PostMapping("/upload")
    public ResponseEntity<List<slideDataModel>> handleFile(@RequestParam("file") MultipartFile file) throws IOException{

        List<slideDataModel> data = pptUploader.pptInfo(file);
        return ResponseEntity.ok(data);
    }
}


// @PostMapping("/extract-slide-xml")
// public ResponseEntity<String> extractAndSaveSlideXml(@RequestParam("file") MultipartFile file) {
//     String entryToExtract = "ppt/slides/slide1.xml"; // adjust this to extract other XML entries
//     String outputPath = "src/main/java/com/server/utils/slide1_output.xml";

//     try (ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream())) {
//         ZipEntry entry;
//         while ((entry = zipInputStream.getNextEntry()) != null) {
//             if (entry.getName().equals(entryToExtract)) {
//                 // Read XML content into memory
//                 ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//                 byte[] temp = new byte[1024];
//                 int len;
//                 while ((len = zipInputStream.read(temp)) != -1) {
//                     buffer.write(temp, 0, len);
//                 }

//                 String xmlContent = buffer.toString("UTF-8");

//                 // Write XML to file
//                 Path outputFilePath = Paths.get(outputPath);
//                 Files.createDirectories(outputFilePath.getParent());
//                 Files.write(outputFilePath, xmlContent.getBytes());

//                 return ResponseEntity.ok(xmlContent);
//             }
//         }

//         return ResponseEntity.status(404).body("slide1.xml not found in the .pptx file.");

//     } catch (IOException e) {
//         return ResponseEntity.status(500).body("Error processing PPTX: " + e.getMessage());
//     }
// }
// }


