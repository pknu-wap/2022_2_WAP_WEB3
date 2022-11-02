package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ReservationEntity;
import com.example.demo.Repository.ReservationRepository;
import com.example.demo.model.dto.ReservationDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
	private List<ReservationDTO> rdtoList = new ArrayList<>();
	
	
//	public List<ReservationDTO> getList() {
	public List<ReservationEntity> getList() {
		List<ReservationEntity> list = reservationRepository.findAll();
		
//		for(int i =0; i < list.size(); i++) {
//			ReservationEntity reservationEntity = list.get(i);
////			if(ReservationEntity.getDate().isAfter(LocalDateTime.now())) {
//				ReservationDTO reservationDTO = new ReservationDTO
//						.Builder()
//						.setLocation(re.getLocation())
//						.setContent(re.getContnet())
//						.setDate(re.getDate())
//						.build();
//				rdtoList.add(reservationDTO);
////			}
//		}
		
//		return rdtoList;
		return list;
	}
//	public void createReservation(ReservationDTO rdto) {
//		
//		// 계층간 분리를 위해서 dto를 올려주는 중이었음
//		ReservationEntity reservationEntity;
//		reservationRepository.save(rdto);
//	}?
//	
//	public void deleteReservation() {
//		/* 사용자 정보를 받아오면 */
//		
//	}
}
