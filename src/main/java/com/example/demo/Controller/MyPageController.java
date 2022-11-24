package com.example.demo.Controller;

import com.example.demo.Service.MyPageService;
import com.example.demo.model.dto.MemberProfileDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;

@RequiredArgsConstructor
@RequestMapping("/mypage")
@Controller
public class MyPageController {
    private final MyPageService myPageService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String mypageForm() { return "/mypage"; }

    @ResponseBody
    @GetMapping("/info")
    public HashMap<String, String> mypage(Principal principal) {
        String email = principal.getName();
        MemberProfileDTO memberProfileDTO = myPageService.getMemberProfileDTO(email);
        String imageId = memberProfileDTO.getImageEntity().getImageId();
 
        HashMap<String, String> profile = new HashMap<>();
        profile.put("name", email);
        profile.put("artistName", memberProfileDTO.getArtistName());
        profile.put("genre", memberProfileDTO.getGenre());
        profile.put("message", memberProfileDTO.getMessage());
        profile.put("imageName", myPageService.findImageName(imageId));
        return profile;
    }

    @PostMapping("/post")
    public String mypage(@RequestParam(required = false) String artistName, @RequestParam(required = false) String genre,
                         @RequestParam(required = false) String message,
                         @RequestParam(required = false) MultipartFile file, Principal principal) {

        String email = principal.getName();

        MemberProfileDTO memberProfileDTO = MemberProfileDTO.builder()
                .artistName(artistName)
                .genre(genre)
                .message(message)
                .build();
        try {
            myPageService.saveMemberProfile(memberProfileDTO, email);
            if(!file.isEmpty() && file != null) { myPageService.saveImageEntity(file, email); }
        } catch(Exception e) {
            logger.info(e.getMessage());
            return "/mypage";
        }

        return "redirect:/mypage";
    }
}
