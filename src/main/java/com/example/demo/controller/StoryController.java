package com.example.demo.controller;

import com.example.demo.data.Story;
import com.example.demo.service.StoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/rest/story")
public class StoryController {

    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity <Story> findStoryById (
            @PathVariable Long id,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        try {
            Story story = storyService.findById(id);
            if (story != null) {
                return ResponseEntity.ok(story);
            }
            throw new Exception("Story not found");
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<String> createStory(HttpServletRequest request, HttpServletResponse response, @RequestBody Story story) throws Exception{

        try {
            boolean created = storyService.save(story);

            if(created) {
                return ResponseEntity.ok("Created new story");
            }

            throw new Exception("Failed creating story");

        } catch (Exception e){

            e.printStackTrace();
            throw e;
        }

    }

    @GetMapping (value = "/get-all")
    public ResponseEntity<List<Story>> getAllStories (HttpServletRequest request, HttpServletResponse response) throws  Exception{
        try {
            List<Story> storyList = storyService.getAllStories();
            if (storyList != null) {
                return ResponseEntity.ok(storyList);
            }
            return (ResponseEntity<List<Story>>) ResponseEntity.notFound();
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("get All not Found");
        }
    }
}
