package goshipcode.hourgoaltracker.model;

import lombok.Data;

import java.util.List;

@Data
public class GoalModelDto {

    private String goalName;
    private List<GoalHour> goalHours;
    private Long timestampCreated;

    @Data
    public static class GoalHour {
        private Boolean completed;
        private Long timeCompleted;
    }
}
