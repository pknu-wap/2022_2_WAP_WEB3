package com.example.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;

@Controller
@RequestMapping("/image")
public class ImageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @GetMapping
    public ResponseEntity<byte[]> image(@RequestParam String imageName) {
        ResponseEntity<byte[]> result = null;
        File file = new File("C:\\springboot\\image\\" + imageName);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(
                    FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch(Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }
}
