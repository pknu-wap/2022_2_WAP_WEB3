package com.example.demo.Controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public String createPost(@RequestBody @Valid PostDTO postdto, Principal principal,
			@RequestParam(value="file", required=false) MultipartFile file) throws Exception {
		if (principal != null) {
			System.out.println("dto : " + postdto.getDate());
			System.out.println("dto : " + postdto.getContent());
			System.out.println("dto : " + postdto.getLocation());
			System.out.println("ID정보 : " + principal.getName());
		} else {
			System.out.println("#########");
		}
		postService.putPost(principal.getName(), postdto, new ImageDTO(file.getOriginalFilename()));
			
		return "redirect:/page/post";
	}

	
	@DeleteMapping(value = "/post")
	public void deletePost(@RequestParam Integer num) {	
		postService.deletePost(num);
	}
}
