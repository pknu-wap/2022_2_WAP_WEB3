package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.PostEntity;
import com.example.demo.Repository.PostRepository;
import com.example.demo.model.dto.PostDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostService {
	@Autowired
	private PostRepository PostRepository;
	private List<PostDTO> rdtoList = new ArrayList<>();

	public List<PostDTO> getList() {
		List<PostEntity> rlist = PostRepository.findAll().stream()
			.filter(e -> e.getDate().isAfter(LocalDateTime.now()))
			.sorted()
			.collect(Collectors.toList());
		
		for (int i = 0; i < rlist.size(); i++) {
			PostEntity list = rlist.get(i);
			PostDTO PostDTO = new PostDTO
					.Builder()
					.setPostNum(list.getPost_num())
					.setTheme(list.getTheme())
					.setLocation(list.getLocation())
					.setContent(list.getInfomation())
					.setDate(list.getDate())
					.build();
			rdtoList.add(PostDTO);
			}
		
		return rdtoList;
}

	public void createPost(PostDTO rdto) {
		PostRepository.save(new PostEntity(
						rdto.getMemberEmail(),
						rdto.getTheme(), 
						rdto.getLocation(), 
						rdto.getInfoamtion(), 
						rdto.getDate())
		);
		
	}

	public void deletePost(int num) {
//		try {
			PostRepository.deleteById(num);
//		} catch (Exception e) {
//			return "deleteErr";
//		}
	}
}
