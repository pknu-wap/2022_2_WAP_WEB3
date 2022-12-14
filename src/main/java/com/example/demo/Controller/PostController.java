package com.example.demo.Controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.PostService;
import com.example.demo.model.dto.PostDTO;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	
//	@GetMapping(value = "/list") 
//	@ResponseBody
//	public List<PostDTO> get() {	
//		List<PostDTO> list = postService.get();
//		return list;
//	}
	 
	@PutMapping(value = "/post")
	public String put(@RequestParam String location , @RequestParam String content, 
			@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") 
			@RequestParam LocalDateTime date, Principal principal,
			@RequestParam(required=false) MultipartFile file) throws Exception {
		
		PostDTO postdto = new PostDTO.Builder()
				.setLocation(location) 
				.setDate(date) 
				.setContent(content)
				.build();
		
		postService.create(principal.getName(), postdto, file);
			
		return "redirect:/";
	}
 
	@PutMapping(value = "/post/{post_num}")
	public String update(@RequestParam String location, @RequestParam String content, 
			@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") @RequestParam LocalDateTime date, Principal principal,
			@PathVariable(name="post_num") Integer post_num, 
			@RequestParam(required=false) MultipartFile file,
			@RequestParam(required=false) String imageName) { 
		
		PostDTO postdto = new PostDTO.Builder() 
				.setPostNum(post_num)
				.setLocation(location)  
				.setDate(date)   
				.setContent(content) 
				.build();
		
		
		postService.update(postdto, file, imageName);  
		return "redirect:/page/post/"+post_num;  
	}

	@DeleteMapping(value = "/post/{post_num}")
	public String delete(@PathVariable(name="post_num") Integer post_num, Principal principal) {	
		postService.delete(principal.getName(), post_num);
		return "root2";  
	}  
	   
	@GetMapping(value = "/post/{post_num}")
	public String getUpdatePage(@PathVariable(name="post_num") Integer post_num, Model model) {
		PostDTO pdto = postService.getPost(post_num);
		model.addAttribute("data", pdto); 
		return "modify"; 
	} 
	
	@GetMapping(value = "/page/post/{post_num}")
	public String page(@PathVariable(name="post_num") Integer post_num, Model model
			) {
		PostDTO pdto = postService.getPost(post_num);
		model.addAttribute("data", pdto);
		return "post";   
	}
	
}
