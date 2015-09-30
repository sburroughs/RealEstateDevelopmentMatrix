package com.fingerprint.nestwood.matrix.messages;

/**
 * Created by SBurroug on 9/5/2015.
 */
public class MatrixNode {

    private String title;
    private String content;
    private Comment rootComment;
    private String auxiliaryInformation;

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

    public String getAuxiliaryInformation() {
        return auxiliaryInformation;
    }

    public void setAuxiliaryInformation(String auxiliaryInformation) {
        this.auxiliaryInformation = auxiliaryInformation;
    }
}
