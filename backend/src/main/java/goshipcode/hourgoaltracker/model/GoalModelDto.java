package goshipcode.hourgoaltracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoalModelDto {

    private String userId;
    private String goalName;
    private List<GoalHourDto> goalHours;
    private Long timestampCreated;

    @Data
    public static class GoalHourDto {
        private Boolean completed;
        private Long timeCompleted;
    }
}
