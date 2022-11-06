package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.PostService;
import com.example.demo.model.dto.PostDTO;

@Controller
public class webController {
	@RequestMapping("/")
	public String root() {	// 메인 페이지
		return "root";
	}
	
//	@RequestMapping("/test")
//	public String rootest() {	
//		return "test";
//	}
	
//	@RequestMapping("/page/club/infomation")
//	public String pageInfomation(Model model) {	// 동아리 소개 페이지
//		model.addAttribute("location", "위치");
//		model.addAttribute("content", "공연 정보");
//		model.addAttribute("date", "시간");
//		return "club-info";
//	}
}
