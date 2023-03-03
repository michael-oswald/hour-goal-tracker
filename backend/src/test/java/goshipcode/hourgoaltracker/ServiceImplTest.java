package goshipcode.hourgoaltracker;

import goshipcode.hourgoaltracker.dao.Repository;
import goshipcode.hourgoaltracker.model.GoalModel;
import goshipcode.hourgoaltracker.model.GoalModelDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ServiceImplTest {

    @InjectMocks
    private ServiceImpl service;

    @Mock
    private Repository repository;

    @Test
    public void testGetGoalModelDoesntExist() {
        //given
        String userId = "userid123";
        Mockito.when(repository.get(anyString())).thenReturn(null); //return null response

        //when
        GoalModelDto actual = service.get(userId);

        //then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals("userid123", actual.getUserId());
    }

    @Test
    public void testGetGoalModel() {
        //given
        String userId = "userId";
        Long timestamp = Instant.now().toEpochMilli();
        List<GoalModel.Goal> goals = new ArrayList<>();
        GoalModel.Goal goal = new GoalModel.Goal();
        goal.setGoalHours(getGoalHours(timestamp));
        goal.setTimestampCreated(timestamp);
        goal.setGoalName("coolGoalName");
        goals.add(goal);
        GoalModel expected = new GoalModel("UserId#userId", goals);
        Mockito.when(repository.get(anyString())).thenReturn(expected);

        //when
        GoalModelDto actual = service.get(userId);

        //then
        Mockito.verify(repository, times(1)).get(anyString());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals("userId", actual.getUserId());
        Assertions.assertEquals(expected.getGoals().get(0).getGoalName(), actual.getGoals().get(0).getGoalName());
        Assertions.assertEquals(expected.getGoals().get(0).getGoalHours().get(0).getCompleted(), actual.getGoals().get(0).getGoalHourDtos().get(0).getCompleted());
        Assertions.assertEquals(expected.getGoals().get(0).getGoalHours().get(0).getTimeCompleted(), actual.getGoals().get(0).getGoalHourDtos().get(0).getTimeCompleted());
    }

    @Test
    public void testPostGoalModel() {
        //given
        Long timestamp = Instant.now().toEpochMilli();

        List<GoalModelDto.GoalDto> goals = new ArrayList<>();
        GoalModelDto.GoalDto goalDto = new GoalModelDto.GoalDto();
        goalDto.setGoalHourDtos(getGoalHoursDto(timestamp));
        goalDto.setTimestampCreated(timestamp);
        goalDto.setGoalName("coolGoalName");
        goals.add(goalDto);

        GoalModelDto goalModelDto = new GoalModelDto("userId", goals);
        ArgumentCaptor<GoalModel> goalModelArgumentCaptor = ArgumentCaptor.forClass(GoalModel.class);

        List<GoalModel.Goal> goalList = new ArrayList<>();
        GoalModel.Goal goal = new GoalModel.Goal();
        goal.setGoalHours(getGoalHours(timestamp));
        goal.setTimestampCreated(timestamp);
        goal.setGoalName("coolGoalName");
        goalList.add(goal);
        GoalModel expected = new GoalModel("userId", goalList);

        //when
        service.post(goalModelDto);

        //then
        Mockito.verify(repository, times(1)).save(goalModelArgumentCaptor.capture());
        Assertions.assertEquals(expected, goalModelArgumentCaptor.getValue());

    }

    public static List<GoalModel.GoalHour> getGoalHours (Long timestamp) {
        List<GoalModel.GoalHour> list = new ArrayList<>();
        IntStream.range(1, 10).forEach(i -> {
            GoalModel.GoalHour goalHour = new GoalModel.GoalHour();
            if (i > 5) {
                goalHour.setCompleted(true);
            } else {
                goalHour.setCompleted(false);
            }

            goalHour.setTimeCompleted(timestamp);
            list.add(goalHour);
        });

        return list;
    }

    public static List<GoalModelDto.GoalHourDto> getGoalHoursDto(Long timestamp) {
        List<GoalModelDto.GoalHourDto> list = new ArrayList<>();
        IntStream.range(1, 10).forEach(i -> {
            GoalModelDto.GoalHourDto goalHour = new GoalModelDto.GoalHourDto();
            if (i > 5) {
                goalHour.setCompleted(true);
            } else {
                goalHour.setCompleted(false);
            }

            goalHour.setTimeCompleted(timestamp);
            list.add(goalHour);
        });

        return list;
    }

}
