package goshipcode.hourgoaltracker;

import goshipcode.hourgoaltracker.dao.Repository;
import goshipcode.hourgoaltracker.model.GoalModel;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceImpl implements Service {

    private Repository repository;

    @Autowired
    public ServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public GoalModel get(String userId) {
        GoalModel goalModel = repository.get(userId);

        return goalModel;
    }
}
