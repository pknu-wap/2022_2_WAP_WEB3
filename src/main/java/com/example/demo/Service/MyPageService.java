package com.example.demo.Service;

import com.example.demo.Entity.ImageEntity;
import com.example.demo.Entity.Member;
import com.example.demo.Entity.MemberProfile;
import com.example.demo.Repository.ImageRepository;
import com.example.demo.Repository.MemberProfileRepository;
import com.example.demo.Repository.MemberRepository;
import com.example.demo.ServerPath;
import com.example.demo.model.dto.MemberProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class    MyPageService {
    private final MemberRepository memberRepository;
    private final MemberProfileRepository memberProfileRepository;
    private final ImageRepository imageRepository;
 
    public MemberProfile saveMemberProfile(MemberProfileDTO memberProfileDTO, String email) {
        MemberProfile memberProfile = memberRepository.findByEmail(email).getMemberProfile();
        memberProfile.updateProfile(memberProfileDTO.getArtistName(), memberProfileDTO.getGenre(), memberProfileDTO.getMessage());
        return memberProfileRepository.save(memberProfile);
    }  
  
    public ImageEntity saveImageEntity(MultipartFile file, String email) {
        ImageEntity imageEntity = memberRepository.findByEmail(email).getMemberProfile().getImageEntity();
        Path exPath = Paths.get(ServerPath.getImagePath() + imageEntity.getImageName());
        try { 
            Files.delete(exPath);
        } catch(Exception e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis();
        System.out.println(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss_");
        String formattedTime = simpleDateFormat.format(time);
        String image_name = formattedTime + file.getOriginalFilename();
        String path = ServerPath.getImagePath() + image_name;

        try {
            file.transferTo(new File(path));
        } catch (IllegalStateException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        imageEntity.updateImage(image_name);
        return imageRepository.save(imageEntity);
    }

    public MemberProfileDTO getMemberProfileDTO(String email) {
        Member member = memberRepository.findByEmail(email);
        MemberProfile memberProfile = member.getMemberProfile();
        return MemberProfileDTO.builder()
                .artistName(memberProfile.getArtistName())
                .genre(memberProfile.getGenre())
                .message(memberProfile.getMessage())
                .imageEntity(memberProfile.getImageEntity())
                .build();
    }

    public String findImageName(String id) {
        Optional<ImageEntity> optionalImageEntity= imageRepository.findById(id);
        if(optionalImageEntity.isPresent()) {
            return optionalImageEntity.get().getImageName();
        }
        else {
            throw new NullPointerException("There is no such imageId");
        }
    }
}
