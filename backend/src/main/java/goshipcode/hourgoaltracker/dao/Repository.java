package goshipcode.hourgoaltracker.dao;

import goshipcode.hourgoaltracker.model.GoalModel;

public interface Repository {
    GoalModel get(String userId);

    void save(GoalModel goalModel);
}
