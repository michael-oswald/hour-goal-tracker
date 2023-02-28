package goshipcode.hourgoaltracker;

import goshipcode.hourgoaltracker.dao.Repository;
import goshipcode.hourgoaltracker.model.GoalModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
        GoalModel expected = new GoalModel("userId", "myCoolGoal", null, null, null);
        Mockito.when(repository.get(anyString())).thenReturn(expected);

        //when
        GoalModel actual = service.get(userId);

        //then
        Mockito.verify(repository, times(1)).get(anyString());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getUserId(), actual.getUserId());
        Assertions.assertEquals(expected.getGoalName(), actual.getGoalName());

    }

}
