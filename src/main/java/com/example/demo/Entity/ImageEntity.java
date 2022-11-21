package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name = "image")
public class ImageEntity {
    @Id
    @Column(length = 36)
    private String imageId;

    @Column(length = 50)
    private String imageName;

    @Builder
    public ImageEntity(String imageId, String imageName) {
        this.imageId = imageId;
        this.imageName = imageName;
    }

    public void updateImage(String imageName) {
        this.imageName = imageName;
    }
}
