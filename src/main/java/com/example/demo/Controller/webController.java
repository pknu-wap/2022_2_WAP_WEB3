package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.ReservationDTO;
import com.example.demo.Entity.ReservationEntity;
import com.example.demo.Repository.ReservationRepository;
import com.example.demo.Service.ReservationService;

@Controller
public class webController {
	@Autowired
	private ReservationService reservationService;
	
	
	@RequestMapping("/")
	public String root() {	// 메인 페이지
		return "root";
	}
	
//	@RequestMapping("/page/club/infomation")
//	public String pageInfomation(Model model) {	// 동아리 소개 페이지
//		model.addAttribute("location", "위치");
//		model.addAttribute("content", "공연 정보");
//		model.addAttribute("date", "시간");
//		return "club-info";
//	}
	
	@RequestMapping("/page/reservation")
	public String pageRreservation(Model model) {	// 메인 페이지
		List<ReservationEntity> list = reservationService.getList();
		
		
		model.addAttribute("list", list);
		System.out.println(list.get(0).getDate());
		return "reservation";
	}
}
