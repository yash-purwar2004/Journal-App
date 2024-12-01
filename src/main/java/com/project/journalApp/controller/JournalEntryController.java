package com.project.journalApp.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.journalApp.entity.JournalEntry;

// A special type of component which is used to handle our HTTP Requests

// We write special end points in this class as a method
// @Request Mapping annotation is used to add mapping in the whole class

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private final Map<Long, JournalEntry> journalEntries = new ConcurrentHashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    // It's like saying, "Hey spring, please take the data from the request and turn it into a java object that I can use in my code"
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }
}
