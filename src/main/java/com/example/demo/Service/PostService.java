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
	
	public String getImage(Integer post_num) {
		try {
			List<PostEntity> list = postRepository.findAll();
			PostEntity post = new PostEntity(); 
			
			for(int i = 0; i<list.size(); i++) {
				if(list.get(i).getPost_num().equals(post_num)) {
					post = list.get(i);
				}
			}
			return post.getImageId().getImageName();   
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null;
		}
	}      
	 
	public PostDTO getPost(Integer post_num) {
		try {
			List<PostEntity> list = postRepository.findAll();
			PostEntity post = new PostEntity(); 
			 
			for(int i = 0; i<list.size(); i++) { 
				if(list.get(i).getPost_num().equals(post_num)) {
					post = list.get(i);
				} 
			}
			
			ImageEntity imageEntity = post.getImageId() != null ? 
					post.getImageId().builder()
					.imageName(post.getImageId().getImageName()).build() 
					: post.getImageId().builder()
					.imageName("").build();
			
			PostDTO postDTO = new PostDTO.Builder()
					.setContent(post.getContent())
					.setStrDate(post.getDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분")).toString())
					.setLocation(post.getLocation())
					.setImageId(imageEntity)
					.build();
			
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
					.setStrDate(entity.getDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분")).toString())
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
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return "fail create";
		}
		try {
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
			
		} catch (Exception e) {
			return "fail create";
		}
		return "create";
	}
	
	public String update(PostDTO rdto, MultipartFile file, String imageName) {
		Optional<PostEntity> post_entity = postRepository.findById(rdto.getPostNum());
		String image_name;
		
		if(file.getOriginalFilename().equals("") && post_entity.get().getImageId() == null) {
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
			
			System.out.println("################"+postEntity.getContent());
			System.out.println("################"+postEntity.getDate());
			System.out.println("################"+postEntity.getLocation()); 
			postRepository.save(postEntity);
			return "update";
			
		} else {
			image_name = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyymmdd_hhmmss")) 
					+ "_" + file.getOriginalFilename();
			String path =  ServerPath.getImagePath() + image_name;
			try {
				file.transferTo(new File(path));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try { 
				String tmp = post_entity.get().getImageId().getImageId().equals("") ? null : post_entity.get().getImageId().getImageId();
				ImageEntity imageEntity = ImageEntity.builder()
						.imageId(tmp)
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
				System.out.println("###############123#"+postEntity.getContent());
				System.out.println("###############123#"+postEntity.getDate());
				System.out.println("###############123#"+postEntity.getLocation()); 
				postRepository.save(postEntity);
				return "update";
			} catch (Exception e) {
				return "fail update";
			}
		}
	}
	
	public void delete(String email, Integer post_num) {
		Optional<PostEntity> postEntity = postRepository.findById(post_num);
		String postEmail = postEntity.get().getEmail();
		if(postEntity.isPresent()) {            
            if(postEmail.equals(email)) {             	
            	postRepository.deleteById(post_num);
            }
        }
        else {
            throw new NullPointerException("There is no such imageId");
        }
	}
	
	public String getPostEmailString(Integer post_num) {
		Optional<PostEntity> postEntity;
		try {
			postEntity = postRepository.findById(post_num);
			return postEntity.get().getEmail();
		} catch(Exception e) {
			e.printStackTrace();
		}
		throw new NullPointerException("There is no such post");
	}
}