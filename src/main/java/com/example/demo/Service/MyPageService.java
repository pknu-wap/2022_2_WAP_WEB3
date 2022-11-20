package com.example.demo.Service;

import com.example.demo.Entity.Member;
import com.example.demo.Entity.MemberProfile;
import com.example.demo.Repository.MemberProfileRepository;
import com.example.demo.Repository.MemberRepository;
import com.example.demo.model.dto.MemberProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MyPageService {
    private final MemberRepository memberRepository;
    private final MemberProfileRepository memberProfileRepository;

    public MemberProfileDTO getMemberProfileDTO(String email) {
        Member member = memberRepository.findByEmail(email);
        MemberProfile memberProfile = member.getMemberProfile();
        return MemberProfileDTO.builder()
                .team(memberProfile.getTeam())
                .showType(memberProfile.getShowType())
                .imageId(memberProfile.getImageEntity().getImageId())
                .introduce(memberProfile.getIntroduce())
                .build();
    }
}
