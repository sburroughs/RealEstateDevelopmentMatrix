package com.fingerprint.nestwood.comments;

import java.util.Date;

/**
 * Created by SBurroug on 9/4/2015.
 */
public class Comment {

    String comment;
    Date postDate;
    String name;

    public Comment() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
