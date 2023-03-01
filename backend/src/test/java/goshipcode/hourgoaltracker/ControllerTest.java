package goshipcode.hourgoaltracker;

import goshipcode.hourgoaltracker.model.GoalModel;
import goshipcode.hourgoaltracker.model.GoalModelDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

    @InjectMocks
    private Controller controller;

    @Mock
    private Service service;

    @Test
    void get() {
        //given
        String userId = "userId";
        GoalModelDto expected = new GoalModelDto("userId", "myCoolGoal", null, null);
        Mockito.when(service.get(anyString())).thenReturn(expected);

        //when
        GoalModelDto actual = controller.get(userId);

        //then
        Mockito.verify(service, times(1)).get(anyString());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getUserId(), actual.getUserId());
    }
}