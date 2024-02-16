package com.example.demo.impl;

import com.example.demo.data.Story;
import com.example.demo.data.StoryRowMapper;
import com.example.demo.repositories.StoryRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JdbcTemplate: It provides several convenience methods for executing SQL queries,
   updating the database, and handling exceptions, making database operations more concise
   and easier to manage compared to traditional JDBC code
 */

@Repository
public class StoryRepositoryImpl implements StoryRepository {

    private final JdbcTemplate jdbcTemplate;
    private final StoryRowMapper storyRowMapper;

    private final String FIND_BY_ID_SQL = "SELECT * FROM  stories WHERE ID = ?";
    private final String SAVE_SQL = "INSERT INTO stories (title, body) VALUES (?,?)";
    private final String GET_ALL_SQL = "SELECT * FROM stories";

    public StoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.storyRowMapper = new StoryRowMapper();
    }

    @Override
    public Story findStoryById(Long storyId) {
        try {
            Story story = jdbcTemplate.queryForObject(FIND_BY_ID_SQL, storyRowMapper, storyId);
            return story;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Story story) {

        try{
            int storyResult = jdbcTemplate.update(
                    SAVE_SQL ,story.getTitle(), story.getBody()

            );
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Story> getAllStory() {

        try{
            List<Story> allStories = jdbcTemplate.query(GET_ALL_SQL, storyRowMapper);
            return allStories;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
