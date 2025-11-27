package com.example.Error_Tracker.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Error_Tracker.entity.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, ObjectId> {
}
