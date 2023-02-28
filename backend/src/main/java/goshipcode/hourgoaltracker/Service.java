package goshipcode.hourgoaltracker;

import goshipcode.hourgoaltracker.model.GoalModel;

public interface Service {
    GoalModel get(String userId);


}
