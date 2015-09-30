package com.fingerprint.nestwood.matrix.dao;

import com.fingerprint.nestwood.matrix.messages.Comment;
import com.fingerprint.nestwood.matrix.messages.DevelopmentMatrix;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;

/**
 * Created by SBurroug on 9/6/2015.
 */
public class CommentsDAO extends MatrixDAO {

    public CommentsDAO() throws PropertyVetoException, IOException {
        super();
    }

    public Comment getComments(int stage, int task) throws SQLException {

        Comment comment = addChildComments(new Comment(), stage, task, 0);

        return comment;
    }


    private Comment addChildComments(Comment parent, int stageId, int taskId, int parentId) throws SQLException {

        String statement = "SELECT * FROM development_matrix.comments WHERE stage_id = ? and task_id = ? AND parent_id = ? ORDER BY timestamp";

        try (Connection connection = super.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(statement)) {
                ps.setInt(1, stageId);
                ps.setInt(2, taskId);
                ps.setInt(3, parentId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String text = rs.getString("comment");
                        String name = rs.getString("username");
                        Date timestamp = rs.getDate("timestamp");

                        Comment comment = new Comment();
                        comment.setName(name);
                        comment.setTimestamp(timestamp);
                        comment.setCommentText(text);

                        //Adds children of current node
                        int updatedParentId = rs.getInt("comment_id");
                        addChildComments(comment, stageId, taskId, updatedParentId);

                        parent.getChildComments().add(comment);

                    }
                }
            }
        }

        return parent;
    }


    public void applyComments(DevelopmentMatrix matrix) throws SQLException {

        try (Connection connection = super.getConnection()) {

            //APPLIES COMMENTS TO Stage
            int stageCount = 1;
            String stageCountStatement = "SELECT COUNT(*) as count FROM development_matrix.headers WHERE type='STAGE'";
            try (PreparedStatement ps = connection.prepareStatement(stageCountStatement)) {
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    stageCount = rs.getInt("count");
                }
            }
            String stageStatement = "SELECT * FROM development_matrix.headers WHERE type='TASK'";
            try (PreparedStatement ps = connection.prepareStatement(stageStatement)) {
                try (ResultSet rs = ps.executeQuery()) {
                    //populate stageheader
                    rs.next();
                    int stageId = rs.getInt("id");
                    Comment comment = addChildComments(new Comment(), stageId, 0, 0);
                    //Applies Comment to header
                    matrix.getStageHeader().getHeaders().get(stageId - 1).setRootComment(comment);
                }
            }

            //APPLIES COMMENTS TO Task
            int taskCount = 1;
            String taskCountStatement = "SELECT COUNT(*) as count FROM development_matrix.headers WHERE type='TASK'";
            try (PreparedStatement ps = connection.prepareStatement(taskCountStatement)) {
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    taskCount = rs.getInt("count");
                }
            }
            String taskStatement = "SELECT * FROM development_matrix.headers WHERE type='TASK'";
            try (PreparedStatement ps = connection.prepareStatement(taskStatement)) {
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    //populate task header
                    int taskId = rs.getInt("id");
                    Comment comment = addChildComments(new Comment(), 0, taskId, 0);
                    //Applies Comment to header
                    matrix.getStageHeader().getHeaders().get(taskId - 1).setRootComment(comment);

                }
            }

            //APPLIES COMMENTS TO CONTENT
            for (int stage = 1; stage < stageCount; stage++) {
                for (int task = 1; task < taskCount; task++) {
                    Comment comment = addChildComments(new Comment(), stage, task, 0);
                    matrix.getMatrix().getContent(stage, task).setRootComment(comment);
                }
            }
        }
    }

}
