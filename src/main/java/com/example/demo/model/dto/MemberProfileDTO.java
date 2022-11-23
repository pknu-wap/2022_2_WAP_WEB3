package com.example.demo.model.dto;

import com.example.demo.Entity.ImageEntity;
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

    private ImageEntity imageEntity;

    @Builder
    public MemberProfileDTO(String artistName, String genre, String message, ImageEntity imageEntity) {
        this.artistName = artistName;
        this.genre = genre;
        this.message = message;
        this.imageEntity = imageEntity;
    }
}
