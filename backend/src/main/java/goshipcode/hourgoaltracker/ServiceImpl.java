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

    @Override
    public void post(GoalModelDto goalModelDto) {
        repository.save(goalModelDtoToGoalModel(goalModelDto));

    }

    private GoalModelDto goalModelToDto(GoalModel goalModel) {

        List<GoalModelDto.GoalDto> goals = new ArrayList<>();

        for (GoalModel.Goal goal : goalModel.getGoals()) {
            List<GoalModelDto.GoalHourDto> goalHourDtos = new ArrayList<>();

            for (GoalModel.GoalHour goalHour : goal.getGoalHours()) {
                GoalModelDto.GoalHourDto goalHourDto = new GoalModelDto.GoalHourDto();
                goalHourDto.setCompleted(goalHour.getCompleted());
                goalHourDto.setTimeCompleted(goalHour.getTimeCompleted());
                goalHourDtos.add(goalHourDto);
            }

            GoalModelDto.GoalDto goalDto = new GoalModelDto.GoalDto();
            goalDto.setGoalName(goal.getGoalName());
            goalDto.setTimestampCreated(goal.getTimestampCreated());
            goalDto.setGoalHourDtos(goalHourDtos);
            goals.add(goalDto);
        }


        String trimmedStr = goalModel.getUserId().substring(7);

        return GoalModelDto.builder()
                        .goals(goals)
                        .userId(trimmedStr).build();
    }

    private GoalModel goalModelDtoToGoalModel(GoalModelDto goalModelDto) {

        List<GoalModel.Goal> list = new ArrayList<>();

        for (GoalModelDto.GoalDto goalDto : goalModelDto.getGoals()) {
            List<GoalModel.GoalHour> goalHours = new ArrayList<>();

            for (GoalModelDto.GoalHourDto goalHourDto : goalDto.getGoalHourDtos()) {
                GoalModel.GoalHour goalHour = new GoalModel.GoalHour();
                goalHour.setCompleted(goalHourDto.getCompleted());
                goalHour.setTimeCompleted(goalHourDto.getTimeCompleted());
                goalHours.add(goalHour);
            }

            GoalModel.Goal goal = new GoalModel.Goal();
            goal.setGoalName(goalDto.getGoalName());
            goal.setTimestampCreated(goalDto.getTimestampCreated());
            goal.setGoalHours(goalHours);
            list.add(goal);
        }

        return GoalModel.builder()
                .goals(list)
                .userId(goalModelDto.getUserId()).build();
    }
}
