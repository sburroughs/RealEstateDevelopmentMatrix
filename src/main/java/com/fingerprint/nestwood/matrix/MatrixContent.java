package com.fingerprint.nestwood.matrix;

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
        return content[stage][task];
    }

    public void addContent(int stage, int task, MatrixNode node) {
        this.content[stage][task] = node;
    }
}
