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
	
	public String getImage(String email, Integer post_num) {
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
	 
	public PostDTO getPost(String email, Integer post_num) {
		try {
			List<PostEntity> list = postRepository.findAll();
			PostEntity post = new PostEntity(); 
			 
			for(int i = 0; i<list.size(); i++) { 
				if(list.get(i).getEmail().equals(email) && list.get(i).getPost_num().equals(post_num)) {
					post = list.get(i);
				} 
			}
			ImageEntity imageEntity = post.getImageId() != null ? 
		               post.getImageId().builder()
		               .imageName(post.getImageId().getImageName()).build() 
		               : (ImageEntity) null;
			
			PostDTO postDTO = new PostDTO.Builder()
					.setContent(post.getContent())
					.setDate(post.getDate())
					.setLocation(post.getLocation())
					.setImageId(imageEntity)
					.build();
			System.out.println(post.getLocation());
			return postDTO;   
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null;
		}
	}  
	  
	public List<PostDTO> get() { 
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
	public String create(String id, PostDTO rdto, MultipartFile file) {
		PostEntity postEntity;
		String image_name = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyymmdd_hhmmss")) 
				+ "_" + file.getOriginalFilename() ;
		String path =  ServerPath.getImagePath() + image_name;
		
		try {	// 같은 이름 파일 처리도 해야함
			file.transferTo(new File(path));
			
			if(file.isEmpty()) {
				 postEntity = PostEntity.builder()
						.email(id)
						.theme(rdto.getTheme())
						.location(rdto.getLocation())
						.content(rdto.getContent())
						.date(rdto.getDate())
						.build();
				
				postRepository.save(postEntity);
			} else {
				ImageEntity imageEntity = ImageEntity.builder()
						.imageId(UUID.randomUUID().toString())
						.imageName(image_name)
						.build();
				
				postEntity = PostEntity.builder()
						.email(id)
						.theme(rdto.getTheme())
						.location(rdto.getLocation())
						.content(rdto.getContent())
						.date(rdto.getDate())
						.ImageId(imageEntity)
						.build();
				
				postRepository.save(postEntity); 
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return "fail create";
		}
		return "create";
	}
	
	public String update(PostDTO rdto, MultipartFile file, String imageName) {
		Optional<PostEntity> post_entity = postRepository.findById(rdto.getPostNum());
		String image_name;
		
		if(file.getOriginalFilename().equals("")) {
			image_name = imageName;
			
			ImageEntity imageEntity = ImageEntity.builder()
					.imageId(post_entity.get().getImageId().getImageId())
					.imageName(image_name)
					.build(); 
			 
			PostEntity postEntity = PostEntity.builder()
					.post_num(rdto.getPostNum())
					.email(post_entity.get().getEmail()) 
					.location(rdto.getLocation())
					.content(rdto.getContent())
					.date(rdto.getDate())
					.ImageId(imageEntity)
					.build();
			
			postRepository.save(postEntity);
			return "update";
			
		} else {
			image_name = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyymmdd_hhmmss")) 
					+ "_" + file.getOriginalFilename();
			
			String path =  ServerPath.getImagePath() + image_name;
			try { 
				file.transferTo(new File(path));
				
				ImageEntity imageEntity = ImageEntity.builder()
						.imageId(post_entity.get().getImageId().getImageId())
						.imageName(image_name)
						.build(); 
				 
				PostEntity postEntity = PostEntity.builder()
						.post_num(rdto.getPostNum())
						.email(post_entity.get().getEmail()) 
						.location(rdto.getLocation())
						.content(rdto.getContent())
						.date(rdto.getDate())
						.ImageId(imageEntity)
						.build();
				
				postRepository.save(postEntity);
				return "update";
			} catch (Exception e) {
				return "fail update";
			}
		}
	}
	
	public String delete(Integer post_num) {
		if(postRepository.findById(post_num).isPresent()) {
			postRepository.deleteById(post_num);
			return "delete";
		} else return "fail delete";
	}
}
