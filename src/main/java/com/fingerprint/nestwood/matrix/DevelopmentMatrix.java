package com.fingerprint.nestwood.matrix;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SBurroug on 8/22/2015.
 */
public class DevelopmentMatrix {

    private List<MatrixStage> stages;

    public DevelopmentMatrix() {
        stages = new ArrayList<>();
    }

    /**
     * @return
     */
    public List<MatrixStage> getStages() {
        return stages;
    }

    public void setStages(List<MatrixStage> stages) {
        this.stages = stages;
    }

    public String generateRequest() {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(stages);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }
}
