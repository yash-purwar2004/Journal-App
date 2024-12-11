package com.project.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // This annotation tells the spring to find the transactional annotation
public class JournalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalAppApplication.class, args);
	}

	// dbFactory makes a connection between  application and the database
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

}

// Springboot makes a transactional context corresponds to method
// So, It treats all operation as a one operation, if any of the operation fail, the whole process will rollback

// Platform Transaction Manager
// Mongo Transaction Manager is an implementation of Platform Transaction Manager
