package com.fingerprint.nestwood.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SBurroug on 8/22/2015.
 */
public class MatrixStage {

    private String title;
    private List<MatrixTask> tasks;

    public MatrixStage() {
        tasks = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MatrixTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<MatrixTask> tasks) {
        this.tasks = tasks;
    }

    public void addMatrixTask(MatrixTask matrixTask) {
        getTasks().add(matrixTask);
    }

}
