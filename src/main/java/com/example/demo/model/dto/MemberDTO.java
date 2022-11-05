package com.example.demo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter @Setter
public class MemberDTO {

    @NotBlank(message = "공백이 없어야 합니다.")
    @Email(message = "이메일 양식이어야 합니다.")
    private String email;

    @NotBlank(message = "공백이 없어야 합니다.")
    @Length(message = "비밀번호는 8글자 이상 16글자 이하여야 합니다.", min = 8, max = 16)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$")
    private String password;

    @Builder
    public MemberDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
