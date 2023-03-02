package goshipcode.hourgoaltracker.dao;

import goshipcode.hourgoaltracker.model.GoalModel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Component
public class RepositoryDDBImpl implements Repository {

    @Value("${dynamodb.table.name}")
    private String dynamodbTableName;

    private static final String KEY_PREFIX = "UserId#";

    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    @Autowired
    public RepositoryDDBImpl(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
    }

    @Override
    public GoalModel get(String userId) {
        DynamoDbTable<GoalModel> table = dynamoDbEnhancedClient.table(dynamodbTableName, TableSchema.fromBean(GoalModel.class));
        Key key = Key.builder()
                .partitionValue(KEY_PREFIX + userId)
                .build();

        // Get the item by using the key.
        GoalModel result = table.getItem((GetItemEnhancedRequest.Builder requestBuilder) -> requestBuilder.key(key));

        return result;
    }

    @Override
    public void save(GoalModel goalModel) {
        DynamoDbTable<GoalModel> table = dynamoDbEnhancedClient.table(dynamodbTableName, TableSchema.fromBean(GoalModel.class));
        goalModel.setUserId(KEY_PREFIX + goalModel.getUserId());
        table.putItem(goalModel);
    }
}
