package org.example.sample.common;

import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.example.sample.users.model.User;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class Utils {

    public static PutItemRequest createPutRequest(User user) {
        log.info("Creating put request for user: {}", user);
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(User.CREATED_DATE_ATTR, AttributeValue.builder()
                .n(Long.toString(user.getCreatedDate().getTime()))
                .build());
        item.put(User.USERNAME_ATTR, AttributeValue.builder()
                .s(user.getUsername())
                .build());
        item.put(User.COUNTRY_CODE_ATTR, AttributeValue.builder()
                .s(user.getCountryCode())
                .build());
        return PutItemRequest.builder()
                .tableName(User.USERS_TABLE)
                .item(item)
                .build();
    }

    public static GetItemRequest createGetRequest(String name) {
        log.info("Creating get request for username: {}", name);
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(User.USERNAME_ATTR, AttributeValue.builder().s(name).build());

        return GetItemRequest.builder()
                .tableName(User.USERS_TABLE)
                .key(key)
                .attributesToGet(User.CREATED_DATE_ATTR, User.COUNTRY_CODE_ATTR, User.USERNAME_ATTR)
                .build();
    }

    public static User getUserFromItemResponse(Map<String, AttributeValue> attributeValueMap) {
        log.info("Response from get request: {}", attributeValueMap);
        if(attributeValueMap.isEmpty()){
            return null;
        }
        return User.builder()
                .createdDate(new Date(Long.valueOf(attributeValueMap.get(User.CREATED_DATE_ATTR).n())))
                .countryCode(attributeValueMap.get(User.COUNTRY_CODE_ATTR).s())
                .username(attributeValueMap.get(User.USERNAME_ATTR).s())
                .build();
    }

    public static User setCreatedDate(User user) {
        user.setCreatedDate(new Date());
        return user;
    }

    public static void mayThrowApplicationError(){
        boolean chance = ThreadLocalRandom.current().nextBoolean();
        if(chance){
            willThrowApplicationError();
        }
    }

    public static void willThrowApplicationError(){
        log.error("Ocurrio un error inesperado");
        throw new WebApplicationException(new WebApplicationException(
                Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Intermitencia en el servicio")
                        .build()
        ));
    }

    public static <T> Uni<T> delayUniBySeconds(Uni<T> uni, int seconds){
        return uni.onItem()
                .delayIt()
                .by(Duration.ofSeconds(seconds));
    }

    public static void delayBy(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
