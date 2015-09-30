package com.fingerprint.nestwood.matrix.dao;

import com.fingerprint.nestwood.matrix.messages.MatrixContent;
import com.fingerprint.nestwood.matrix.messages.MatrixNode;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SBurroug on 9/27/2015.
 */
public class MatrixContentDAO extends MatrixDAO {

    private static int MAX_STAGE = 7;
    private static int MAX_TASK = 8;

    public MatrixContentDAO() throws PropertyVetoException, IOException {
        super();
    }

    public MatrixContent getContent() throws SQLException {

        MatrixContent matrix = new MatrixContent(MAX_STAGE, MAX_TASK);

        try (Connection connection = super.getConnection()) {
            String statement = "SELECT * FROM development_matrix.content WHERE  task_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(statement)) {
                for (int i = 1; i <= MAX_STAGE; i++) {
                    ps.setInt(1, i);
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            String content = rs.getString("content");
                            String title = rs.getString("title");

                            MatrixNode node = new MatrixNode();
                            node.setContent(content);
                            node.setTitle(title);

                            int task = rs.getInt("task_id");
                            int stage = rs.getInt("stage_id");

                            matrix.addContent(stage, task, node);
                        }
                    }
                }
            }
        }

        return matrix;
    }


}
