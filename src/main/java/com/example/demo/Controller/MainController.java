package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Service.PostService;
import com.example.demo.model.dto.PostDTO;

@Controller
public class MainController {
	@Autowired
	private PostService postService;  
	
    @GetMapping(value = "/")  
    public String main(Model model) {
    	List<PostDTO> list = postService.get();  
		model.addAttribute("postlist", list);
        return "root2";
    }
       
    @GetMapping(value = "/page/mypage")
    public String mypage() { 
        return "mypage";
    }
     
    @GetMapping(value = "/page/enroll")
	public String pageEnroll() {	
		return "enroll";
	}
}  
