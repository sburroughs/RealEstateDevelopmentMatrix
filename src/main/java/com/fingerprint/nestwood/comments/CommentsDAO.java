package com.fingerprint.nestwood.comments;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;

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

        String statement = "SELECT * FROM development_matrix.comments WHERE task_id = ? AND development_matrix.parent_id is null";
        Connection connection = cpds.getConnection();
        PreparedStatement ps = connection.prepareStatement(statement);
        ps.setString(1, hash(stage, task));
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            String text = rs.getString("comment");
            String name = rs.getString("username");
            Date timestamp = rs.getDate("timestamp");

            Comment comment = new Comment();
            comment.setName(name);
            comment.setTimestamp(timestamp);
            comment.setCommentText(text);


        }

        return null;
    }



    public Comment getComments(int parentid){

    }

    public String hash(int stage, int task) {
        return "" + stage + task;
    }


}
