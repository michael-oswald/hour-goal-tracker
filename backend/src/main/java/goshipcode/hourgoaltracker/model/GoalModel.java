package goshipcode.hourgoaltracker.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

@DynamoDbBean
@Data
public class GoalModel {

    private String emailId;
    private String goalName;
    private List<GoalHour> goalHours;
    private Long timestampCreated;

    @DynamoDbPartitionKey
    public String getEmailId() {
        return emailId;
    }

    @Data
    public static class GoalHour {
        private Boolean completed;
        private Long timeCompleted;
    }
}
