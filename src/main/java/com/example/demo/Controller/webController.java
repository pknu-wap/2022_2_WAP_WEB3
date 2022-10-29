package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class webController {
	@RequestMapping("/")
	public String root() {	// 메인 페이지
		return "root";
	}
	
	@RequestMapping("/page/club/infomation")
	public String pageInfomation(Model model) {	// 동아리 소개 페이지
		model.addAttribute("location", "위치");
		model.addAttribute("content", "공연 정보");
		model.addAttribute("date", "시간");
		return "club-info";
	}
	
	@RequestMapping("/page/reservation")
	public String pageRreservation() {	// 메인 페이지
		return "reservation";
	}
}
