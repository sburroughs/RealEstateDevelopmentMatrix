package com.fingerprint.nestwood.comments;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SBurroug on 9/6/2015.
 */
public class CommentsDAO {

    private ComboPooledDataSource cpds;

    public CommentsDAO() throws PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver
        cpds.setJdbcUrl("jdbc:mysql://localhost/development_matrix");
        cpds.setUser("sburroughs");
        cpds.setPassword("derp1234");
    }


    public Comment getComments(int stage, int task) throws SQLException {

        String statement = "SELECT * FROM development_matrix.comments WHERE task_id = ?";
        Connection connection = cpds.getConnection();
        PreparedStatement ps = connection.prepareStatement(statement);
        ResultSet rs = ps.executeQuery();

        return null;
    }

    public String hash(int stage, int task) {
        return "" + stage + task;
    }


}
