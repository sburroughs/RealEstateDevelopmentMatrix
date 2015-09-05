package com.fingerprint.nestwood.matrix;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by SBurroug on 9/5/2015.
 */
public class JSON {

    public static String toJson(Object object) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }
}
