package com.example.demo.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.ServerPath;
import com.example.demo.Entity.ImageEntity;
import com.example.demo.Entity.PostEntity;
import com.example.demo.Repository.ImageRepository;
import com.example.demo.Repository.PostRepository;
import com.example.demo.model.dto.PostDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	private List<PostDTO> rdtoList = new ArrayList<>();

	public List<PostDTO> getList() {
		List<PostEntity> list = postRepository.findAll();
		
		for (int i = 0; i < list.size(); i++) {
			PostEntity entity = list.get(i);
			if(entity.getDate().isAfter(LocalDateTime.now())) {
				PostDTO PostDTO = new PostDTO
					.Builder()
					.setPostNum(entity.getPost_num())
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

	public void putPost(String email, PostDTO rdto, MultipartFile file) {
//		Optional<PostEntity> post_entity = postRepository.findById(rdto.getPostNum());
		String image_name = file.getOriginalFilename();
		String path = ServerPath.getImagePath() + image_name;
		
		try {
			file.transferTo(new File(path));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PostEntity postEntity = PostEntity.builder()
				.member_email(email)
				.theme(rdto.getTheme())
				.location(rdto.getLocation())
				.content(rdto.getContent())
				.date(rdto.getDate())
				.build();
		
		
		if(rdto.getPostNum()==null && image_name==null) { //put & 이미지 없음
			postRepository.save(postEntity);
			System.out.println(postEntity.getLocation());
		} else if(rdto.getPostNum()==null && image_name != null) {	// put & 이미지 있음
			postRepository.save(postEntity.builder()
					.image(ImageEntity.builder()
							.imageId(UUID.randomUUID().toString())
							.imageName(image_name)
							.build())
					.build());
			System.out.println(postEntity.getLocation());
				
		} else {	// update
			Optional<PostEntity> post_entity = postRepository.findById(rdto.getPostNum());
			postRepository.save(postEntity.builder()
					.post_num(rdto.getPostNum())
					.image(ImageEntity.builder()
							.imageId(post_entity.get().getImage().getImageId())
							.imageName(image_name)
							.build())
					.build());
			System.out.println(postEntity.getLocation());
		}
	}
	
//	public void put() {
//		
//	}
//	
	public void deletePost(int num) {
		try {
			postRepository.deleteById(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
