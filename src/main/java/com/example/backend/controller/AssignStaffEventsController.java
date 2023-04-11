package com.example.backend.controller;


import com.example.backend.exception.AssignStaffEventsNotFoundException;
import com.example.backend.exception.StaffNotFoundException;
import com.example.backend.model.AssignStaffEvents;
import com.example.backend.repository.AssignStaffEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class AssignStaffEventsController {

    @Autowired
    private AssignStaffEventsRepository assignStaffEventsRepository;

    //postMapping==Post data to the db
    @PostMapping("/assignStaffEvents")
    AssignStaffEvents newAssignStaffEvents(@RequestBody AssignStaffEvents newAssignStaffEvents){
        return assignStaffEventsRepository.save(newAssignStaffEvents);
    }
    //getMapping==Get data to the db
    @GetMapping("/assignStaffEventsS")
    List<AssignStaffEvents>getAllassignStaffEvents(){return assignStaffEventsRepository.findAll();}

   @GetMapping("/assignStaffEvents/{id}")
        AssignStaffEvents getassignStaffEventsById(@PathVariable Long id) {
                    return assignStaffEventsRepository.findById(id)
                            .orElseThrow(()->new AssignStaffEventsNotFoundException(id));
    }

    @PutMapping("/assignStaffEvents/{id}")
      AssignStaffEvents updateAssignStaffEvents(@RequestBody AssignStaffEvents newAssignStaffEvents,@PathVariable Long id){
        return assignStaffEventsRepository.findById(id)
                .map(assignStaffEvents -> {
                    assignStaffEvents.setStaffName(newAssignStaffEvents.getStaffName());
                    assignStaffEvents.setDate(newAssignStaffEvents.getDate());
                    assignStaffEvents.setTime(newAssignStaffEvents.getTime());
                    assignStaffEvents.setDuty(newAssignStaffEvents.getDuty());
                    assignStaffEvents.setVenue(newAssignStaffEvents.getVenue());
                        return assignStaffEventsRepository.save(assignStaffEvents);
                }).orElseThrow(()->new AssignStaffEventsNotFoundException(id));
    }

    @DeleteMapping("/assignStaffEvents/{id}")
    String deleteAssignStaffEvents(@PathVariable Long id){
        if (!assignStaffEventsRepository.existsById(id)){
            throw new AssignStaffEventsNotFoundException(id);
        }
        assignStaffEventsRepository.deleteById(id);
        return "User with id "+id+" has been deleted success.";
        }




}
