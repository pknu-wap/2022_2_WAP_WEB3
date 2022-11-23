
package com.example.demo.Controller;

import java.io.File;
import java.nio.file.Files;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.PostService;

@Controller
@RequestMapping("/image")
public class ImageController {
	@Autowired 
	PostService postService;
	
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
