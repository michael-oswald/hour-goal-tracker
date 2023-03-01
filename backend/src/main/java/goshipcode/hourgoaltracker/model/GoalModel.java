package goshipcode.hourgoaltracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

@DynamoDbBean
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalModel {

    private String userId;
    private String goalName;
    private List<GoalHour> goalHours;
    private Long timestampCreated;
    private Long lastUpdated;

    @DynamoDbPartitionKey
    public String getUserId() {
        return userId;
    }

    @Data
    @DynamoDbBean
    public static class GoalHour {
        private Boolean completed;
        private Long timeCompleted;
    }
}
