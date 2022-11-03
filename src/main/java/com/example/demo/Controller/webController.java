package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.print.attribute.standard.MediaSize.ISO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping(value = "/page/reservation")
	public String pageRreservation() {	
		return "reservation";
	}
	
	@GetMapping(value = "/reservation")
	@ResponseBody
	public List<ReservationDTO> getList() {	
		List<ReservationDTO> list = reservationService.getList();
		return list;
	}
	
	@PutMapping(value = "/reservation")
	public String createReservation(
			@RequestParam String content, 
			@RequestParam String location, 
			@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") @RequestParam LocalDateTime date)
	{
		reservationService.createReservation(new ReservationDTO
				.Builder()
				.setLocation(location)
				.setContent(content)
				.setDate(date)
				.build()
		);
		return "redirect:/";
	}
	
	@DeleteMapping(value = "/reservation")
	public void deleteReservation(@RequestParam Integer num) {
		reservationService.deleteReservation(num);
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
