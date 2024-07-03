package com.davidosorno.events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.davidosorno.events.models.Event;




@Repository
public interface EventRepo extends CrudRepository <Event, Long> {
    List<Event> findByState(String state);

	List<Event> findByStateIsNot(String state);

	Event findEventById(Long event_id);
}