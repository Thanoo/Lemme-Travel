package com.lemmetravel.seniorproject.Model;

import java.io.Serializable;

public class AuthorData implements Serializable {

    /**
     * member_id : 1
     * username : gulufuu
     * profile_img : 1488873733_p.jpg
     */

    private String member_id;
    private String username;
    private String profile_img;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }
}
