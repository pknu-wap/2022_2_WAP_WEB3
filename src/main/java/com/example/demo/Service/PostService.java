package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ImageEntity;
import com.example.demo.Entity.PostEntity;
import com.example.demo.Repository.PostRepository;
import com.example.demo.model.dto.ImageDTO;
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
					.setImageId(entity.getImage())
					.build();
			
				rdtoList.add(PostDTO);
			}
		}
		
		return rdtoList;
}

	public void putPost(PostDTO rdto, ImageDTO idto) {
		Optional<PostEntity> post_entity;
		String image_id = idto.getImage();
		if(image_id != null)
			post_entity = postRepository.findById(rdto.getPostNum());
		else {
			if(rdto.getPostNum()==null && image_id==null) { //put & 이미지 없음
				postRepository.save(PostEntity.builder()
						// 이메일
						// 테마
						.location(rdto.getLocation())
						.content(rdto.getContent())
						.date(rdto.getDate())
						.build());
			} else if(rdto.getPostNum()!=null) {	// put & 이미지 있음
				postRepository.save(PostEntity.builder()
						// 이메일
						// 테마
						.location(rdto.getLocation())
						.content(rdto.getContent())
						.date(rdto.getDate())
						.image(ImageEntity.builder()
								.imageId(image_id)
								.imageName(UUID.randomUUID().toString())
								.build())
						.build());
				
			} else {	// update
				postRepository.save(PostEntity.builder()
						.post_num(rdto.getPostNum())
						// 이메일
						// 테마
						.location(rdto.getLocation())
						.content(rdto.getContent())
						.date(rdto.getDate())
						.image(ImageEntity.builder()
								.imageId(post_entity.get().getImage().getImageId())
								.imageName(image_id)
								.build())
						.build());
			}
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
