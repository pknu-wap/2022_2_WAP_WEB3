package com.example.demo.Controller;

import com.example.demo.Service.MyPageService;
import com.example.demo.model.dto.MemberProfileDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

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
        profile.put("artistName", memberProfileDTO.getArtistName());
        profile.put("genre", memberProfileDTO.getGenre());
        profile.put("message", memberProfileDTO.getMessage());
        return profile;
    }

    @PostMapping("/mypage/post")
    public String mypage(@RequestParam String artistName, @RequestParam String genre,
                         @RequestParam String message,
                         @RequestParam(required = false) MultipartFile file, Principal principal) {

        String email = principal.getName();

        MemberProfileDTO memberProfileDTO = MemberProfileDTO.builder()
                .artistName(artistName)
                .genre(genre)
                .message(message)
                .build();
        try {
            myPageService.saveMemberProfile(memberProfileDTO, email);
            if(file != null) { myPageService.saveImageEntity(file, email); }
        } catch(IllegalStateException e) {
            logger.info(e.getMessage());
            return "/mypage";
        }

        return "redirect:/page/mypage";
    }
}
