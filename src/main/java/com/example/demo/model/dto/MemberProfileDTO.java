package com.example.demo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class MemberProfileDTO {
    private String team;

    private String showType;

    private String imageId;

    private String introduce;

    @Builder
    public MemberProfileDTO(String team, String showType, String imageId, String introduce) {
        this.team = team;
        this.showType = showType;
        this.imageId = imageId;
        this.introduce = introduce;
    }
}
