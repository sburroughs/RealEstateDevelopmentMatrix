package com.fingerprint.nestwood.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SBurroug on 8/22/2015.
 */
public class DevelopmentMatrix {

    List<MatrixStage> stages;

    public DevelopmentMatrix() {
        stages = new ArrayList<>();
    }

    public List<MatrixStage> getStages() {
        return stages;
    }

    public void setStages(List<MatrixStage> stages) {
        this.stages = stages;
    }

}
