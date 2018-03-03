package com.example.wr.crawler.data.remote.dto;


/**
 * Created by WR on 2017-11-27.
 */

public class ImageDTO {

    private String imgSrc;
    private String caption;

    public ImageDTO(String imgSrc, String caption) {
        this.imgSrc = imgSrc;
        this.caption = caption;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
