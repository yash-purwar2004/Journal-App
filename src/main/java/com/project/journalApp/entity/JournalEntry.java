package com.project.journalApp.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


// This is called POJO - Plain Old Java Object
@Document(collection = "journal_entries")
@Data
public class JournalEntry {
    @Id  // acts as a primary key
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
}
