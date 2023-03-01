package goshipcode.hourgoaltracker;

import goshipcode.hourgoaltracker.model.GoalModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/goal/{userId}")
    GoalModelDto get(@PathVariable String userId) {
        GoalModelDto goalModelDto = service.get(userId);

        return goalModelDto;
    }
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
