package goshipcode.hourgoaltracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class SpringConfig {

    @Value("${dynamodb.local.endpoint:}") //optional
    String localDynamoDbEndpoint;

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient() {
        DynamoDbClient ddb = null;
        if (localDynamoDbEndpoint.isBlank()) { //use normal AWS dynamodb in cloud
            ddb= DynamoDbClient.builder()
                    .region(Region.US_EAST_1)
                    .build();
        } else { // use local dynamodb endpoint
            ddb = DynamoDbClient.builder()
                    .region(Region.US_EAST_1)
                    .endpointOverride(URI.create(localDynamoDbEndpoint))
                    .build();
        }

        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(ddb)
                .build();
        return enhancedClient;
    }

}
