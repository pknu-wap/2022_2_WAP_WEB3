package com.example.demo.Controller;

import com.example.demo.Entity.Member;
import com.example.demo.Service.MemberService;
import com.example.demo.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

/**
 * /members/login 로그인 -> /
 * /members/sign_up 회원가입 -> /members/login
 * /members/logout 로그아웃 -> /
 * /members/login/error 로그인 실패 시 주소
 * /members/emailCheck?email="" 주소창으로 이메일 받아 가용 여부 json 리턴 {"isValid" : true|false}
 * /members/loginCheck 로그인 여부 json 리턴 {"isLogin" : "email"|null}
 */
@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 회원가입 페이지 리턴
    @GetMapping(value = "/sign_up")
    public String memberForm() {
        return "member/sign_up";
    }

    /**
     * 회원가입 요청 처리
     * @param memberDto email, password
     * @param bindingResult 바인딩 error
     * @return 성공 시 /members/login, 비정상적 가입 시도 시 /members/sign_up
     */
    @PostMapping(value = "/sign_up")
    public String memberForm(@ModelAttribute("memberDto") @Valid MemberDTO memberDto, BindingResult bindingResult) { // @Valid를 사용하면 DTO에 명시한 validation을 사용할 수 있음.
        if (bindingResult.hasErrors()) {
            List<ObjectError> list = bindingResult.getAllErrors();
            for(ObjectError error : list) {
                logger.info(error.toString());
            }
            return "member/sign_up";
        }
        try {
            Member member = Member.createMember(memberDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            logger.info(e.getMessage());
            return "member/sign_up";
        }
        return "redirect:/members/login";
    }

    /**
     * RequestParam 주소창으로 이메일을 받아 중복 확인 후 가용 여부 리턴
     * @param email 이메일
     * @return key:"isValid", value:true|false
     */
    @ResponseBody
    @GetMapping(value = "/emailCheck")
    public HashMap<String, Boolean> emailCheck(@RequestParam(required = false) String email) {
        boolean check = memberService.validateEmail(email);
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("isValid", check);
        return map;
    }

    // 로그인 페이지 리턴
    @GetMapping(value = "/login")
    public String loginMember() {
        return "/member/login";
    }

    // 로그인 에러 났을 때
    @GetMapping(value = "/login/error")
    public String loginError() {
        return "/member/login";
    }

    /**
     * 사용자의 토큰 보유 여부를 통해 로그인 확인
     * 토큰이 있을 경우 username, 없을 경우 null
     * @param principal 스프링 시큐리티 토큰
     * @return key:"isLogin", value:"username"|null
     */
    @ResponseBody
    @GetMapping(value = "/loginCheck")
    public HashMap<String, String> loginMember(Principal principal) {
        HashMap<String, String> map = new HashMap<>();
        try {
            map.put("isLogin", principal.getName());
        } catch(NullPointerException e) {
            map.put("isLogin", null);
        }
        return map;
    }
}