package com.example.demo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class MemberProfileDTO {
    private String artistName;

    private String genre;

    private String message;

    @Builder
    public MemberProfileDTO(String artistName, String genre, String message) {
        this.artistName = artistName;
        this.genre = genre;
        this.message = message;
    }
}
