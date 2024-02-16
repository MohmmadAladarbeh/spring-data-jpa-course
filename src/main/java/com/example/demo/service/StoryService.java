package com.example.demo.service;

import com.example.demo.data.Story;
import com.example.demo.repositories.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The service class uses StoryRepository to fetch data from db.
 */

@Service
public class StoryService {

    private final StoryRepository storyRepository;

    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public Story findById (Long storyId) {
        return storyRepository.findStoryById(storyId);
    }

    public boolean save (Story story) {
        return storyRepository.save(story);
    }

    public List<Story> getAllStories (){
        return storyRepository.getAllStory();
    }
}
