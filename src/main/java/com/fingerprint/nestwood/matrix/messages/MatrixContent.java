package com.fingerprint.nestwood.matrix.messages;

/**
 * Created by SBurroug on 9/5/2015.
 */
public class MatrixContent {

    private MatrixNode[][] content;

    public MatrixContent(int stageSize, int taskSize) {
        this.content = new MatrixNode[stageSize][taskSize];
    }

    public MatrixNode[][] getContent() {
        return content;
    }

    public MatrixNode getContent(int stage, int task) {
        return content[stage - 1][task - 1];
    }

    public void addContent(int stage, int task, MatrixNode node) {
        this.content[stage - 1][task - 1] = node;
    }

}
