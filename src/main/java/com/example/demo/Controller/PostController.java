package com.example.demo.Controller;

import java.io.File;
import java.nio.file.Files;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.PostService;
import com.example.demo.model.dto.PostDTO;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	
	@GetMapping(value = "/post") 
	@ResponseBody
	public List<PostDTO> getList() {	
		List<PostDTO> list = postService.getList();
		
		return list;
	}
	
	@PutMapping(value = "/post")
	public String createPost(@RequestParam String location, @RequestParam String content, 
			@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") 
			@RequestParam LocalDateTime date, Principal principal,
			@RequestParam(required=false) MultipartFile file) throws Exception {
		
		PostDTO postdto = new PostDTO.Builder()
				.setLocation(location)
				.setDate(date) 
				.setContent(content)
				.build();
		
		postService.putPost(principal.getName(), postdto, file);
			
		return "redirect:/page/enroll";
	}

	@DeleteMapping(value = "/post")
	public void deletePost(@RequestParam Integer num) {	
		postService.deletePost(num);
	}
	
	@GetMapping(value = "/page/post/{post_num}")
	public String pagePost(@PathVariable(name="post_num") Integer post_num, Model model) {
		model.addAttribute("post_num", post_num);
		return "post";
	}
	
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
            System.out.println("#############t" + result);
        } catch(Exception e) {
            e.getMessage();
        }
        return result;
    }
}
