package goshipcode.hourgoaltracker;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody  newEmployee) {
        return repository.save(newEmployee);
    }

    @PutMapping("/employees/{id}")
    void replaceEmployee(/*@RequestBody Employee newEmployee, @PathVariable Long id*/) {

        return;
    }
}
