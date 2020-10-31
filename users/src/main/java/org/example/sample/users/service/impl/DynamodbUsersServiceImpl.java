package org.example.sample.users.service.impl;

import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.example.sample.common.Utils;
import org.example.sample.users.model.User;
import org.example.sample.users.service.UsersService;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
@Slf4j
public class DynamodbUsersServiceImpl implements UsersService {

    @Inject
    private DynamoDbClient dynamoDbClient;

    @Override
    public Uni<User> createUser(User user){
        return Uni.createFrom().item(user)
                .map(Utils::setCreatedDate)
                .invoke(u -> dynamoDbClient.putItem(Utils.createPutRequest(u)));
    }

    @Override
    public Uni<User> getUserByUsername(String username){
        return Uni.createFrom().item(dynamoDbClient.getItem(Utils.createGetRequest(username)))
                .onItem().ifNull().failWith(new NotFoundException("No encontramos este usuario"))
                .onItem().transform(getItemResponse -> getItemResponse.item())
                .onItem().transform(Utils::getUserFromItemResponse);
    }


}
