package com.example.demo.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.example.demo.Repository.PostRepository;
import com.example.demo.model.dto.PostDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostService {
	@Autowired
	private PostRepository postRepository; 
	
	public String getPost(String email, Integer post_num) {
		try {
			List<PostEntity> list = postRepository.findAll();
			PostEntity post = new PostEntity(); 
			
			for(int i = 0; i<list.size(); i++) {
				if(list.get(i).getEmail().equals(email) && list.get(i).getPost_num().equals(post_num)) {
					post = list.get(i);
				}
			}
			return post.getImageId().getImageName();   
		} catch (Exception e) { 
			e.printStackTrace();
			return null;
		}
	}   
	  
	public List<PostDTO> getList() { 
		List<PostDTO> dtoList = new ArrayList<>();
		List<PostEntity> list = postRepository.findAll();
		 
		for (int i = 0; i < list.size(); i++) {
			PostEntity entity = list.get(i);
			if(entity.getDate().isAfter(LocalDateTime.now())) {
				PostDTO PostDTO = new PostDTO
					.Builder()   
					.setMemberEmail(entity.getEmail())
					.setPostNum(entity.getPost_num())
					.setLocation(entity.getLocation())
					.setContent(entity.getContent())
					.setDate(entity.getDate())
					.build(); 
			
				dtoList.add(PostDTO);
			}
		}
		return dtoList;
}

	public void putPost(String email, PostDTO rdto, MultipartFile file) {
		String image_name = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyymmdd_hhmmss")) 
				+ "_" + file.getOriginalFilename() ;
		String path =  ServerPath.getImagePath() + image_name;
		
		try {	// 같은 이름 파일 처리도 해야함
			file.transferTo(new File(path));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(rdto.getPostNum()==null && file.isEmpty()) { //put & 이미지 없음
			PostEntity postEntity = PostEntity.builder()
					.email(email)
					.theme(rdto.getTheme())
					.location(rdto.getLocation())
					.content(rdto.getContent())
					.date(rdto.getDate())
					.build();
			
			postRepository.save(postEntity);
			
		} else if(rdto.getPostNum()==null && !file.isEmpty()) {	// put & 이미지 있음
			ImageEntity imageEntity = ImageEntity.builder()
					.imageId(UUID.randomUUID().toString())
					.imageName(image_name)
					.build();
			
			PostEntity postEntity = PostEntity.builder()
					.email(email)
					.theme(rdto.getTheme())
					.location(rdto.getLocation())
					.content(rdto.getContent())
					.date(rdto.getDate())
					.ImageId(imageEntity)
					.build();
			
			postRepository.save(postEntity); 	
				
		} else {	// update
			Optional<PostEntity> post_entity = postRepository.findById(rdto.getPostNum());
			ImageEntity imageEntity = ImageEntity.builder()
					.imageId(post_entity.get().getImageId().getImageId())
					.imageName(image_name)
					.build();
			
			PostEntity postEntity = PostEntity.builder()
					.post_num(rdto.getPostNum())
					.email(email)
					.theme(rdto.getTheme())
					.location(rdto.getLocation())
					.content(rdto.getContent())
					.date(rdto.getDate())
					.ImageId(imageEntity)
					.build();
			
			postRepository.save(postEntity);
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
