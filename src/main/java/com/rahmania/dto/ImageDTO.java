package com.rahmania.dto;

/**
 * Created by bahaa on 10/03/18.
 */
public class ImageDTO {

    String imageName;

    public ImageDTO(String imageName) {
        this.imageName = imageName;
    }

    public ImageDTO() {
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
