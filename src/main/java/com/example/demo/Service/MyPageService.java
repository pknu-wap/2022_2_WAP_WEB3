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

@RequiredArgsConstructor
@Transactional
@Service
public class MyPageService {
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
        String image_name = file.getOriginalFilename();
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
                .build();
    }
}
