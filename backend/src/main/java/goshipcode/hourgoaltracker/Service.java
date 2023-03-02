package goshipcode.hourgoaltracker;

import goshipcode.hourgoaltracker.model.GoalModelDto;

public interface Service {
    GoalModelDto get(String userId);

    void post(GoalModelDto goalModelDto);
}
