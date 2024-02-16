package com.example.demo.data;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * RowMapper: is an interface in Spring JDBC that is used to map a row of a JDBC ResultSet to an object.
 * StoryRowMapper: class will read each row from query result and create a Story object for each row.
 */
public class StoryRowMapper implements RowMapper<Story> {

    @Override
    public Story mapRow(ResultSet resultSet, int i) throws SQLException {
        Story story = new Story();
        story.setId(resultSet.getLong("ID"));
        story.setTitle(resultSet.getString("TITLE"));
        story.setBody(resultSet.getString("BODY"));
        story.setCreatedAt(Timestamp.valueOf(resultSet.getString("CREATED_AT")));
        return story;
    }
}
