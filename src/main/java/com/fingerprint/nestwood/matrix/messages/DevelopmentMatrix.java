package com.fingerprint.nestwood.matrix.messages;

/**
 * Created by SBurroug on 8/22/2015.
 */
public class DevelopmentMatrix {

    private MatrixContent matrix;
    private MatrixHeader stageHeader;
    private MatrixHeader taskHeader;

    public DevelopmentMatrix(MatrixHeader stageHeader, MatrixHeader taskHeader) {
        matrix = new MatrixContent(stageHeader.getHeaders().size(), taskHeader.getHeaders().size());
        this.stageHeader = stageHeader;
        this.taskHeader = taskHeader;
    }

    public MatrixContent getMatrix() {
        return matrix;
    }

    public void setMatrix(MatrixContent matrix) {
        this.matrix = matrix;
    }

    public MatrixHeader getStageHeader() {
        return stageHeader;
    }

    public void setStageHeader(MatrixHeader stageHeader) {
        this.stageHeader = stageHeader;
    }

    public MatrixHeader getTaskHeader() {
        return taskHeader;
    }

    public void setTaskHeader(MatrixHeader taskHeader) {
        this.taskHeader = taskHeader;
    }


}
