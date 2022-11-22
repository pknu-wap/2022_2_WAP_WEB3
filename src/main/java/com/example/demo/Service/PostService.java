package com.example.demo.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
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
	
		public ImageEntity getPost(Integer post_num) throws IOException {
			ImageEntity imageEntity = postRepository.findById(post_num).orElseGet(null).getImageId();
			InputStream imageStream = new FileInputStream(ServerPath.getImagePath() + imageEntity.getFileServerName());
			byte[] imageByteArray = IOUtils.
					(imageStream);
			imageStream.close();
			return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
			
		}
	/*
	 * */
	public List<PostDTO> getList() {
		List<PostDTO> rdtoList = new ArrayList<>();
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
					.setImageId(entity.getImageId())
					.build();
			
				rdtoList.add(PostDTO);
			}
		}
		return rdtoList;
}

	public void putPost(String email, PostDTO rdto, MultipartFile file) {
		String image_name = file.getOriginalFilename() ;
		String path = ServerPath.getImagePath() + image_name;
		
		try {	// 같은 이름 파일 처리도 해야함
			file.transferTo(new File(path));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(rdto.getPostNum()==null && file.isEmpty()) { //put & 이미지 없음
			PostEntity postEntity = PostEntity.builder()
					.member_email(email)
					.theme(rdto.getTheme())
					.location(rdto.getLocation())
					.content(rdto.getContent())
					.date(rdto.getDate())
					.build();
			
			postRepository.save(postEntity);
			
		} else if(rdto.getPostNum()==null && !file.isEmpty()) {	// put & 이미지 있음
			ImageEntity imageEntity = ImageEntity.builder()
					.ImageId(UUID.randomUUID().toString())
					.FileOriginName(image_name)
					.FileServerName(image_name + new SimpleDateFormat("mmddhhmmss").toString())
					.FilePath(path)
					.build();
			
			PostEntity postEntity = PostEntity.builder()
					.member_email(email)
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
					.ImageId(post_entity.get().getImageId().getImageId())
					.FileOriginName(image_name)
					.build();
			
			PostEntity postEntity = PostEntity.builder()
					.post_num(rdto.getPostNum())
					.member_email(email)
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
