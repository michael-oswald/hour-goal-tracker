package goshipcode.hourgoaltracker.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

@DynamoDbBean
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoalModel {

    private String userId;
    private List<Goal> goals;

    @DynamoDbPartitionKey
    public String getUserId() {
        return userId;
    }

    @Data
    @DynamoDbBean
    public static class Goal {
        private String goalName;

        private List<GoalModel.GoalHour> goalHours;

        private Long timestampCreated;
    }

    @Data
    @DynamoDbBean
    public static class GoalHour {
        private Boolean completed;
        private Long timeCompleted;
    }
}
