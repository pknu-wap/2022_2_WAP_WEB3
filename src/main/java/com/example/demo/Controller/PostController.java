package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.PostService;
import com.example.demo.model.dto.ImageDTO;
import com.example.demo.model.dto.PostDTO;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	
	@GetMapping(value = "/page/post")
	public String pageRPost() {	
		return "post";
	}
	
	@GetMapping(value = "/post")
	@ResponseBody
	public List<PostDTO> getList() {	
		List<PostDTO> list = postService.getList();
		return list;
	}
	
	@PutMapping(value = "/post")
	public String createPost(@RequestParam(required=false) Integer number, 
			@RequestParam(required=false) String member_email, 
			@RequestParam(required=false) String theme, @RequestParam String location, 
			@RequestParam String content, 
			@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") 
			@RequestParam LocalDateTime date,
			@RequestParam(value="file", required=false) MultipartFile file) throws Exception {
		
		PostDTO pdto = new PostDTO
				.Builder()
				.setPostNum(number)
				.setMemberEmail(member_email)
				.setTheme(theme)
				.setLocation(location)
				.setContent(content)
				.setDate(date)
				.build();
		
		ImageDTO idto = new ImageDTO(file.getOriginalFilename());
		postService.putPost(pdto, idto);
			
		return "redirect:/page/post";
	}

	
	@DeleteMapping(value = "/post")
	public void deletePost(@RequestParam Integer num) {	
		postService.deletePost(num);
	}
}
