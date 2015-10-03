package com.fingerprint.nestwood.matrix.dao;

import com.fingerprint.nestwood.matrix.messages.HeaderType;
import com.fingerprint.nestwood.matrix.messages.MatrixHeader;
import com.fingerprint.nestwood.matrix.messages.MatrixNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SBurroug on 9/27/2015.
 */
public class MatrixHeaderDAO extends MatrixDAO {

    private Logger log = LogManager.getLogger(MatrixHeaderDAO.class);

    public MatrixHeaderDAO() throws PropertyVetoException, IOException {
        super();
    }

    public MatrixHeader getHeader(HeaderType type) throws SQLException {

        log.trace("Retrieving MatrixHeader: " + type.toString());

        List<MatrixNode> headers = new ArrayList<>();

        String statement = "SELECT * FROM development_matrix.headers WHERE type = ? ORDER BY id";
        try (Connection connection = super.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(statement)) {
                ps.setString(1, type.toString());
                try (ResultSet rs = ps.executeQuery()) {
                    log.debug("Results received for query: " + ps.toString());
                    while (rs.next()) {
                        String title = rs.getString("title");
                        String content = rs.getString("content");

                        MatrixNode node = new MatrixNode();
                        node.setTitle(title);
                        node.setContent(content);

                        headers.add(node);
                    }
                }
            }
        }


        MatrixHeader header = new MatrixHeader();
        header.setHeaders(headers);

        log.trace("Returning MatrixHeader: " + type.toString());

        return header;
    }


}
