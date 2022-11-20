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

    private String team;

    private String showType;

    private String introduce;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private ImageEntity imageEntity;

    @Builder
    public MemberProfile(String team, String showType, String introduce, String email) {
        this.team = team;
        this.showType = showType;
        this.imageEntity = ImageEntity.builder().imageId(UUID.randomUUID().toString()).build();
        this.introduce = introduce;
    }
}
