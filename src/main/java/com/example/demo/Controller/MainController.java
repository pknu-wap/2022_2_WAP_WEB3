package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = "/")
    public String main() {
        return "root";
    }
    
    @GetMapping(value = "/page/mypage")
    public String mypage() {
        return "mypage";
    }
}
