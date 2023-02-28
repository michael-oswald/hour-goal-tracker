package goshipcode.hourgoaltracker.dao;

import goshipcode.hourgoaltracker.model.GoalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Component
public class RepositoryDDBImpl implements Repository {

    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    @Autowired
    public RepositoryDDBImpl(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
    }

    @Override
    public GoalModel get(String userId) {
        return null;
    }

    @Override
    public void put(GoalModel goalModel) {

    }
}
