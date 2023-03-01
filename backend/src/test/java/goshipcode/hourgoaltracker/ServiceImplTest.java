package goshipcode.hourgoaltracker;

import goshipcode.hourgoaltracker.dao.Repository;
import goshipcode.hourgoaltracker.model.GoalModel;
import goshipcode.hourgoaltracker.model.GoalModelDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ServiceImplTest {

    @InjectMocks
    private ServiceImpl service;

    @Mock
    private Repository repository;

    @Test
    public void testGetGoalModel() {
        //given
        String userId = "userId";
        Long timestamp = Instant.now().toEpochMilli();
        GoalModel expected = new GoalModel("UserId#userId", "myCoolGoal", getGoalHours(), timestamp, timestamp + 10L);
        Mockito.when(repository.get(anyString())).thenReturn(expected);

        //when
        GoalModelDto actual = service.get(userId);

        //then
        Mockito.verify(repository, times(1)).get(anyString());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals("userId", actual.getUserId());
        Assertions.assertEquals(expected.getGoalName(), actual.getGoalName());
        Assertions.assertEquals(expected.getGoalHours().get(0).getCompleted(), actual.getGoalHours().get(0).getCompleted());
        Assertions.assertEquals(expected.getGoalHours().get(3).getTimeCompleted(), actual.getGoalHours().get(3).getTimeCompleted());
    }

    private List<GoalModel.GoalHour> getGoalHours () {
        List<GoalModel.GoalHour> list = new ArrayList<>();
        IntStream.range(1, 10).forEach(i -> {
            GoalModel.GoalHour goalHour = new GoalModel.GoalHour();
            if (i > 5) {
                goalHour.setCompleted(true);
            } else {
                goalHour.setCompleted(false);
            }

            goalHour.setTimeCompleted(Instant.now().toEpochMilli());
            list.add(goalHour);
        });

        return list;
    }

}
