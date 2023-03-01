package goshipcode.hourgoaltracker;

import goshipcode.hourgoaltracker.dao.Repository;
import goshipcode.hourgoaltracker.model.GoalModel;
import goshipcode.hourgoaltracker.model.GoalModelDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ServiceImpl implements Service {

    private Repository repository;

    @Autowired
    public ServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public GoalModelDto get(String userId) {
        GoalModel goalModel = repository.get(userId);

        log.debug("successfully fetched GoalModel from repository {}", goalModel);
        return goalModelToDto(goalModel);
    }

    private GoalModelDto goalModelToDto(GoalModel goalModel) {

        List<GoalModelDto.GoalHourDto> list = new ArrayList<>();

        for (GoalModel.GoalHour goalHour : goalModel.getGoalHours()) {
            GoalModelDto.GoalHourDto goalHourDto = new GoalModelDto.GoalHourDto();
            goalHourDto.setCompleted(goalHour.getCompleted());
            goalHourDto.setTimeCompleted(goalHour.getTimeCompleted());
            list.add(goalHourDto);
        }
        String trimmedStr = goalModel.getUserId().substring(7);

        return GoalModelDto.builder()
                        .goalHours(list)
                        .timestampCreated(goalModel.getTimestampCreated())
                        .goalName(goalModel.getGoalName())
                        .userId(trimmedStr).build();
    }
}
