package com.example.demo.Controller;

import com.example.demo.Entity.Member;
import com.example.demo.Service.MemberService;
import com.example.demo.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/join")
    public String memberForm() {
        return "member/sign_up";
    }

    // 회원가입
    @PostMapping(value = "/join")
    public String memberForm(@ModelAttribute("memberDto") @Valid MemberDTO memberDto, BindingResult bindingResult, Model model) { // @Valid를 사용하면 DTO에 명시한 validation을 사용할 수 있음.
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
            model.addAttribute("errorMessage", e.getMessage());
            return "member/sign_up";
        }
        return "redirect:/members/login";
    }

    /* 이메일 중복 체크
    @ResponseBody
    @PostMapping(value = "/emailCheck")
    public HashMap<String, String> emailCheck(@RequestBody HashMap<String, String> hashMap, Error error) {

    }
    */

    @GetMapping(value = "/login")
    public String loginMember() {
        return "/member/login";
    }

    // 로그인 에러 핸들링
    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "/member/login";
    }
}