package goshipcode.hourgoaltracker.dao;

import goshipcode.hourgoaltracker.model.GoalModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.BeanTableSchema;

import java.util.function.Consumer;

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

    @Test
    void get() {
        //given
        ReflectionTestUtils.setField(repositoryDDB, "dynamodbTableName", "hour-goal-tracker");
        String userId = "userId";
        GoalModel expected = new GoalModel("userId", "myCoolGoal", null, null, null);
        Mockito.when(dynamoDbEnhancedClient.table(anyString(), any(BeanTableSchema.class))).thenReturn(dynamoDbTableMock);
        Mockito.when(dynamoDbTableMock.getItem(any(Consumer.class))).thenReturn(expected);

        //when
        GoalModel actual = repositoryDDB.get(userId);

        //then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getUserId(), actual.getUserId());
        Assertions.assertEquals(expected.getGoalName(), actual.getGoalName());

    }
}