package com.fingerprint.nestwood.matrix.messages;

/**
 * Created by SBurroug on 9/5/2015.
 */
public class MatrixNode {

    private String title;
    private String content;
    private Comment rootComment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment getRootComment() {
        return rootComment;
    }

    public void setRootComment(Comment rootComment) {
        this.rootComment = rootComment;
    }

}
