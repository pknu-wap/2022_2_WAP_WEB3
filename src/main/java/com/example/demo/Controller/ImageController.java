package com.example.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.InputStream;

@Controller
@RequestMapping("/image")
public class ImageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public @ResponseBody byte[] image(@RequestParam String imageName) {
        String res = "C:\\springboot\\image\\" + imageName;
        try {
            InputStream inputStream = new FileInputStream(res);
            return inputStream.readAllBytes();
        } catch(Exception e) {
            logger.info(e.getMessage());
        }
        return null;
    }
}
