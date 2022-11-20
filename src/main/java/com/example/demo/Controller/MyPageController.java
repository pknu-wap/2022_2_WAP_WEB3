package com.example.demo.Controller;

import com.example.demo.Service.MemberService;
import com.example.demo.Service.MyPageService;
import com.example.demo.model.dto.MemberProfileDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;

@RequiredArgsConstructor
@RequestMapping("/page")
@Controller
public class MyPageController {
    private final MyPageService myPageService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/mypage")
    public String mypageForm() { return "/mypage"; }

    @ResponseBody
    @GetMapping("/mypage/info")
    public HashMap<String, String> mypage(Principal principal) {
        String email = principal.getName();
        MemberProfileDTO memberProfileDTO = myPageService.getMemberProfileDTO(email);
        HashMap<String, String> profile = new HashMap<>();
        profile.put("name", email);
        profile.put("team", memberProfileDTO.getTeam());
        profile.put("showType", memberProfileDTO.getShowType());
        profile.put("image", "/image/?id=" + memberProfileDTO.getImageId());
        profile.put("introduce", memberProfileDTO.getIntroduce());
        return profile;
    }
}
