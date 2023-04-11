package com.example.backend.controller;

import com.example.backend.exception.StaffNotFoundException;
import com.example.backend.model.Staff;
import com.example.backend.repository.StaffRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    //postMapping==Post data to the db
    @PostMapping("/staff")
    Staff newStaff(@RequestBody Staff newStaff){
        return staffRepository.save(newStaff);
    }

    //getMapping==Get data to the db
    @GetMapping("/staffs")
    List<Staff>getAllstaff(){
        return staffRepository.findAll();
    }

    @GetMapping("/staff/{id}")
    Staff getStaffById(@PathVariable Long id){
        return staffRepository.findById(id)
                .orElseThrow(()->new StaffNotFoundException(id));
    }

    @PutMapping("/staff/{id}")
    Staff updateStaff(@RequestBody Staff newStaff,@PathVariable Long id){
        return staffRepository.findById(id)
                .map(staff -> {
                    staff.setEmail(newStaff.getEmail());
                    staff.setStaffName(newStaff.getStaffName());
                    staff.setContact(newStaff.getContact());
                    return staffRepository.save(staff);
                }).orElseThrow(()->new StaffNotFoundException(id));
    }

    @DeleteMapping("/staff/{id}")
    String deleteStaff(@PathVariable Long id){
        if (!staffRepository.existsById(id)){
            throw new StaffNotFoundException(id);
        }
        staffRepository.deleteById(id);
        return "User with id "+id+" has been deleted success.";
    }
}
