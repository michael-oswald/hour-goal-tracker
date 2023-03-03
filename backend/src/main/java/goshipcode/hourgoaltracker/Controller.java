package goshipcode.hourgoaltracker;

import goshipcode.hourgoaltracker.model.GoalModelDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/goal/{userId}")
    @CrossOrigin
    GoalModelDto get(@PathVariable String userId) {
        GoalModelDto goalModelDto = service.get(userId);

        return goalModelDto;
    }

    @PostMapping("/goal")
    void post(@RequestBody @Valid GoalModelDto goalModelDto) {
        service.post(goalModelDto);
    }

    //can have put be smart, and any portion of the goalmodeldto can be filled in, and you just update that one part.
/*
    @PostMapping("/employees")
    Object newEmployee() {
        return null;
    }

    @PutMapping("/employees/{id}")
    void replaceEmployee(*//*@RequestBody Employee newEmployee, @PathVariable Long id*//*) {

        return;
    }*/
}
