package goshipcode.hourgoaltracker.dao;

import goshipcode.hourgoaltracker.model.GoalModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.BeanTableSchema;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static goshipcode.hourgoaltracker.ServiceImplTest.getGoalHours;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RepositoryDDBImplTest {

    @InjectMocks
    private RepositoryDDBImpl repositoryDDB;

    @Mock
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    @Mock
    private DynamoDbTable dynamoDbTableMock;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(repositoryDDB, "dynamodbTableName", "hour-goal-tracker");
    }

    @Test
    void get() {
        //given
        String userId = "userId";
        List<GoalModel.Goal> goals = new ArrayList<>();
        GoalModel.Goal goal = new GoalModel.Goal();
        goal.setGoalHours(getGoalHours(Instant.now().toEpochMilli()));

        goal.setGoalName("coolGoalName");
        goals.add(goal);
        GoalModel expected = new GoalModel("UserId#userId", goals);
        Mockito.when(dynamoDbEnhancedClient.table(anyString(), any(BeanTableSchema.class))).thenReturn(dynamoDbTableMock);
        Mockito.when(dynamoDbTableMock.getItem(any(Consumer.class))).thenReturn(expected);

        //when
        GoalModel actual = repositoryDDB.get(userId);

        //then
        Mockito.verify(dynamoDbTableMock, times(1)).getItem(any(Consumer.class));
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getUserId(), actual.getUserId());
        Assertions.assertEquals(expected.getGoals().get(0).getGoalName(), actual.getGoals().get(0).getGoalName());

    }

    @Test
    void save() {
        //given
        GoalModel goalModel = new GoalModel("userId", null);
        Mockito.when(dynamoDbEnhancedClient.table(anyString(), any(BeanTableSchema.class))).thenReturn(dynamoDbTableMock);
        Mockito.doNothing().when(dynamoDbTableMock).putItem(any(GoalModel.class));

        ArgumentCaptor<GoalModel> goalModelArgumentCaptor = ArgumentCaptor.forClass(GoalModel.class);

        //when
        repositoryDDB.save(goalModel);

        //then
        Mockito.verify(dynamoDbTableMock, times(1)).putItem(goalModelArgumentCaptor.capture());
        Assertions.assertEquals("UserId#userId", goalModelArgumentCaptor.getValue().getUserId());


    }
}