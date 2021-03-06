package com.linoge.controllers.rest;

import com.linoge.models.entities.Tag;
import com.linoge.servicies.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Timo on 29.12.2016.
 */
@RestController
@RequestMapping("/")
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping(path = "/gettags", method = RequestMethod.GET)
    public List<Tag> getTags(){ return tagService.getAll(); }

    @RequestMapping(path = "/addtag", method = RequestMethod.POST)
    public void addTag(@RequestParam("title") String title){
        tagService.addTag(title);
    }

    @RequestMapping(path = "/deletetag", method = RequestMethod.POST)
    public void deleteTag(@RequestParam("id") Long id){
        tagService.deleteTagById(id);
    }
}
