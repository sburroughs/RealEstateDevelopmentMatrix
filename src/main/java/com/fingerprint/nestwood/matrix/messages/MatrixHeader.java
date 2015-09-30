package com.fingerprint.nestwood.matrix.messages;

import java.util.List;

/**
 * Created by SBurroug on 9/5/2015.
 */
public class MatrixHeader {

    private List<MatrixNode> headers;

    public List<MatrixNode> getHeaders() {
        return headers;
    }

    public void setHeaders(List<MatrixNode> headers) {
        this.headers = headers;
    }

}
