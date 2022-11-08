package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	private PostRepository postRepository;
	private List<PostDTO> rdtoList = new ArrayList<>();

	public List<PostDTO> getList() {
		List<PostEntity> list = postRepository.findAll();
		
		for (int i = 0; i < list.size(); i++) {
			PostEntity entity = list.get(i);
			if(entity.getDate().isAfter(LocalDateTime.now())) {
				PostDTO PostDTO = new PostDTO
					.Builder()
					.setPostNum(entity.getPost_num())
					.setTheme(entity.getTheme())
					.setLocation(entity.getLocation())
					.setContent(entity.getContent())
					.setDate(entity.getDate())
					.build();
			
				rdtoList.add(PostDTO);
			}
		}
		
		return rdtoList;
}

	public void putPost(PostDTO rdto) {
		if(rdto.getPostNum() == null) {		//put
			postRepository.save(new PostEntity(
					//rdto.getMemberEmail(),
					"test@naver.com",
					//rdto.getTheme(), 
					"test theme",
					rdto.getLocation(), 
					rdto.getContent(), 
					rdto.getDate()));
		} else {		//update
		postRepository.save(new PostEntity(
					rdto.getPostNum(),
					//rdto.getMemberEmail(),
					"test@naver.com",
					//rdto.getTheme(), 
					"test theme",
					rdto.getLocation(), 
					rdto.getContent(), 
					rdto.getDate()));
		}
	}

	public void deletePost(int num) {
		try {
			postRepository.deleteById(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
