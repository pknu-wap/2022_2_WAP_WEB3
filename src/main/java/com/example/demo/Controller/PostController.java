package com.example.demo.Controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public List<PostDTO> get() {	
		List<PostDTO> list = postService.get();
		return list;
	}
	
	@PutMapping(value = "/post")
	public String put(@RequestParam String location, @RequestParam String content, 
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") 
			@RequestParam LocalDateTime date, Principal principal,
			@RequestParam(required=false) MultipartFile file) throws Exception {
		
		PostDTO postdto = new PostDTO.Builder()
				.setLocation(location)
				.setDate(date) 
				.setContent(content)
				.build();
		
		postService.create(principal.getName(), postdto, file);
			
		return "redirect:/page/enroll";
	}
	
	@PutMapping(value = "/post/{post_num}")
	public String update(@RequestParam String location, @RequestParam String content, 
			@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") @RequestParam LocalDateTime date, Principal principal,
			@PathVariable(name="post_num") Integer post_num, 
			@RequestParam(required=false) MultipartFile file) {
		
		PostDTO postdto = new PostDTO.Builder()
				.setPostNum(post_num)
				.setLocation(location)
				.setDate(date) 
				.setContent(content)
				.build();
		
		postService.update(postdto, file);
		return "redirect:/page/"+post_num;
	}

	@DeleteMapping(value = "/post")
	public void delete(@RequestParam Integer post_num) {	
		postService.delete(post_num);
	}
	
	@GetMapping(value = "/page/post/{post_num}")
	public String page(@PathVariable(name="post_num") Integer post_num, Model model
			, Principal principal) {
		PostDTO pdto = postService.getPost(principal.getName(), post_num);
		model.addAttribute("data", pdto);
		model.addAttribute("post_num", post_num);
		return "post"; 
	}
	
}
