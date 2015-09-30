package com.fingerprint.nestwood.matrix;

import com.fingerprint.nestwood.matrix.dao.CommentsDAO;
import com.fingerprint.nestwood.matrix.dao.MatrixContentDAO;
import com.fingerprint.nestwood.matrix.dao.MatrixHeaderDAO;
import com.fingerprint.nestwood.matrix.messages.DevelopmentMatrix;
import com.fingerprint.nestwood.matrix.messages.HeaderType;
import com.fingerprint.nestwood.matrix.messages.MatrixContent;
import com.fingerprint.nestwood.matrix.messages.MatrixHeader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;


public class MatrixBuilder {

    Logger logger = LogManager.getLogger(MatrixBuilder.class);

    CommentsDAO commentsDAO;
    MatrixContentDAO matrixDAO;
    MatrixHeaderDAO headerDAO;

    public MatrixBuilder() {
        try {
            commentsDAO = new CommentsDAO();
            matrixDAO = new MatrixContentDAO();
            headerDAO = new MatrixHeaderDAO();
        } catch (PropertyVetoException | IOException e) {
            logger.error("Issue instantiating MatrixBuilder resource", e);
        }
    }


    public DevelopmentMatrix buildMatrix() throws SQLException {

        MatrixContent content = matrixDAO.getContent();

        MatrixHeader stage = headerDAO.getHeader(HeaderType.STAGE);
        MatrixHeader task = headerDAO.getHeader(HeaderType.TASK);

        DevelopmentMatrix matrix = new DevelopmentMatrix(stage, task);
        matrix.setMatrix(content);

        commentsDAO.applyComments(matrix);

        return matrix;
    }


}
