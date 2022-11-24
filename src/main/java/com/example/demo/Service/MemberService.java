package com.example.demo.Service;

import com.example.demo.Entity.Member;
import com.example.demo.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    /**
     * 중복 검사 후, 멤버를 DB에 저장
     * @param member 멤버 객체
     * @return 저장된 멤버 객체, Exception
     */
    public Member saveMember(Member member) { 
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 상태입니다");
        }
        else return memberRepository.save(member);
    }

    /**
     * 이메일을 받아 중복을 확인
     * @param email 이메일
     * @return 중복여부
     */
    public boolean validateEmail(String email) {
        Member findMember = memberRepository.findByEmail(email);
        return findMember == null;
    }

    // 스프링 시큐리티가 username 받아 user 찾을 때 사용하는 함수
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if(member == null) {
            throw new UsernameNotFoundException(email);
        }
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
