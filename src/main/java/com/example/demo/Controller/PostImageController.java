package com.example.demo.Controller;

import java.io.File;
import java.nio.file.Files;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.PostService;

@Controller
public class PostImageController {
	@Autowired
	private PostService postService;
	
	@ResponseBody
    @GetMapping(value = "/post/info")
    public ResponseEntity<byte[]> image(@RequestParam(name="post_num", required=false) Integer post_num, Principal principal, Model model) {
    	String imageName = postService.getPost(principal.getName(), post_num);
        ResponseEntity<byte[]> result = null;
        File file = new File("C:\\springboot\\image\\" + imageName);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(
                    FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK); 
        } catch(Exception e) {    
            e.getMessage();  
        }  
        System.out.println(result);  
        return result;   
    }
}
