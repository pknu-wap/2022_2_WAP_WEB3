package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Entity.ReservationEntity;
import com.example.demo.Service.ReservationService;
import com.example.demo.model.dto.ReservationDTO;
import com.fasterxml.jackson.databind.util.JSONPObject;

@Controller
public class webController {
	@Autowired
	private ReservationService reservationService;
	
	
	@RequestMapping("/")
	public String root() {	// 메인 페이지
		return "root";
	}
	
	@RequestMapping("/test")
	public String rootest() {	// 메인 페이지
		return "test";
	}
	
	@RequestMapping("/testv")
	@ResponseBody
	public List<ReservationEntity> test() {	// 메인 페이지
		List<ReservationEntity> list = reservationService.getList();
		return list;
	}
	
//	@RequestMapping("/page/club/infomation")
//	public String pageInfomation(Model model) {	// 동아리 소개 페이지
//		model.addAttribute("location", "위치");
//		model.addAttribute("content", "공연 정보");
//		model.addAttribute("date", "시간");
//		return "club-info";
//	}
	
//	@RequestMapping("/page/reservation")
//	public String pageRreservation(Model model) {	// 메인 페이지
//		List<ReservationEntity> list = reservationService.getList();
//		model.addAttribute("list", list);
//		return "reservation";
//	}
//	
//	public String createReservation(Model model, String location, String content, LocalDateTime date) {
//		ReservationDTO reservationDTO = new ReservationDTO(location, content, date);
//		reservationService.createReservation(reservationDTO);
//		return "";
//	}
}
