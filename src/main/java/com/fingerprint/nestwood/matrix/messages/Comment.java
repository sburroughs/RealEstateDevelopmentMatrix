package com.fingerprint.nestwood.matrix.messages;

import java.util.Date;
import java.util.List;

/**
 * Created by SBurroug on 9/4/2015.
 */
public class Comment {


    private String commentText;
    private List<Comment> childComments;
    private String name;
    private Date timestamp;

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public List<Comment> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<Comment> childComments) {
        this.childComments = childComments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
