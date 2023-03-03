package goshipcode.hourgoaltracker.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String userId;

    @NotNull
    private List<@Valid GoalDto> goals;

    @Data
    public static class GoalDto {
        @NotNull
        private String goalName;

        @NotNull
        private List<@Valid GoalHourDto> goalHours;

        @NotNull
        private Long timestampCreated;
    }

    @Data
    public static class GoalHourDto {
        @NotNull
        private Boolean completed;
        @NotNull
        private Long timeCompleted;
    }

}
