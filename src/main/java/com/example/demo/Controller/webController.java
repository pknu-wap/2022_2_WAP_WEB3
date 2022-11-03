package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Entity.ReservationEntity;
import com.example.demo.Service.ReservationService;
import com.example.demo.model.dto.ReservationDTO;

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
	
	@RequestMapping(value = "/reservation", method = RequestMethod.GET)
	public String pageRreservation() {	// 메인 페이지
		List<ReservationEntity> list = reservationService.getList();
		return "reservation";
	}
	
	//put
	@RequestMapping(value = "/reservation", method = RequestMethod.PUT)
	public String createReservation(String location, String content, LocalDateTime date) {
		System.out.println(location + " " + content);
		ReservationDTO reservationDTO = new ReservationDTO(location, content, date);
		reservationService.createReservation(reservationDTO);
		return "redirect:/";
	}
	
	//delete
	@RequestMapping(value = "/reservation", method = RequestMethod.DELETE)
	public void deleteReservation(/* 유저정보 */) {
		reservationService.deleteReservation(/* 유저정보 */);
	}
}
