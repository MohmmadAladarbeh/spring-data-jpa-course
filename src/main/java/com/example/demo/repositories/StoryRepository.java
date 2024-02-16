package com.example.demo.repositories;

import com.example.demo.data.Story;

import java.util.List;

public interface StoryRepository {

    public Story findStoryById(Long storyId);

    public boolean save(Story story);

    public List<Story> getAllStory();
}
