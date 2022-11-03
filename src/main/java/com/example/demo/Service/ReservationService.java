package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	public List<ReservationDTO> getList() {
		List<ReservationEntity> rlist = reservationRepository.findAll().stream()
			.filter(e -> e.getDate().isAfter(LocalDateTime.now()))
			.sorted()
			.collect(Collectors.toList());
		
		for (int i = 0; i < rlist.size(); i++) {
			ReservationEntity list = rlist.get(i);
			ReservationDTO reservationDTO = new ReservationDTO
					.Builder()
					.setNum(list.getNum())
					.setLocation(list.getLocation())
					.setContent(list.getContent())
					.setDate(list.getDate())
					.build();
			rdtoList.add(reservationDTO);
			}
		
		return rdtoList;
}

	public void createReservation(ReservationDTO rdto) {
		reservationRepository.save(
				new ReservationEntity(rdto.getLocation(), rdto.getContent(), rdto.getDate())
		);
	}

	public void deleteReservation(int num) {
//		try {
			reservationRepository.deleteById(num);
//		} catch (Exception e) {
//			return "deleteErr";
//		}
	}
}
