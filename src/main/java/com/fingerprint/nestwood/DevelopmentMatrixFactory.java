package com.fingerprint.nestwood;

import com.fingerprint.nestwood.matrix.MatrixBuilder;
import com.fingerprint.nestwood.matrix.messages.DevelopmentMatrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * Created by SBurroug on 9/29/2015.
 */
public class DevelopmentMatrixFactory {

    private static Logger logger = LogManager.getLogger(DevelopmentMatrixFactory.class);

    private static DevelopmentMatrix matrix = null;
    private static DevelopmentMatrixFactory ourInstance = new DevelopmentMatrixFactory();

    private DevelopmentMatrixFactory() {
        refresh();
    }

    public static DevelopmentMatrix getInstance() {
        return matrix;
    }

    public static void refresh() {
        try {
            MatrixBuilder builder = new MatrixBuilder();
            DevelopmentMatrix updated = builder.buildMatrix();
            matrix = updated;
        } catch (SQLException e) {
            logger.error("Unable to build matrix", e);
        }
    }
}
