package com.example.demo.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Table(name = "profile")
@Entity
public class MemberProfile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    private String artistName;

    private String genre;

    private String message;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private ImageEntity imageEntity;

    @Builder
    public MemberProfile(String artistName, String genre, String message) {
        this.artistName = artistName;
        this.genre = genre;
        this.imageEntity = ImageEntity.builder().imageId(UUID.randomUUID().toString()).build();
        this.message = message;
    }

    public void updateProfile(String artistName, String genre, String message) {
        this.artistName = artistName;
        this.genre = genre;
        this.message = message;
    }
}
