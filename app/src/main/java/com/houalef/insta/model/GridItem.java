package com.houalef.insta.model;

/**
 * Created by Mehdi on 09/09/2017.
 */

public class GridItem {

    public static final int Header = 0;
    public static final int Image = 1;

    int type;
    String title;
    SocialPicture socialPicture;

    // constructors

    public GridItem(String title) {
        this.type = Header;
        this.title = title;
    }

    public GridItem(SocialPicture socialPicture) {
        this.type = Image;
        this.socialPicture = socialPicture;
    }


    // getters

    public int getType() {
        return type;
    }

    public SocialPicture getSocialPicture() {
        return socialPicture;
    }

    public String getTitle() {
        return title;
    }


}
