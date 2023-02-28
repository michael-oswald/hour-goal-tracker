package goshipcode.hourgoaltracker;

import goshipcode.hourgoaltracker.model.GoalModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @GetMapping("/employees")
    List<Object> all() {
        return null;
    }

    @PostMapping("/employees")
    Object newEmployee() {
        return null;
    }

    @PutMapping("/employees/{id}")
    void replaceEmployee(/*@RequestBody Employee newEmployee, @PathVariable Long id*/) {

        return;
    }
}
