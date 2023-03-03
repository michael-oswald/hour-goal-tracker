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
    @CrossOrigin
    void post(@RequestBody @Valid GoalModelDto goalModelDto) {
        service.post(goalModelDto);
    }
}
