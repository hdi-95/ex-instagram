package com.houalef.insta.model;

import java.io.Serializable;

/**
 * Created by Mehdi on 09/09/2017.
 */

public class SocialPicture implements Serializable {
    String thumbnail;
    String standard_resolution;
    String text;

    // constructor

    public SocialPicture(String thumbnail, String standard_resolution, String text) {
        this.thumbnail = thumbnail;
        this.standard_resolution = standard_resolution;
        this.text = text;
    }

    // getters

    public String getThumbnail() {
        return thumbnail;
    }

    public String getStandard_resolution() {
        return standard_resolution;
    }

    public String getText() {
        return text;
    }
}
