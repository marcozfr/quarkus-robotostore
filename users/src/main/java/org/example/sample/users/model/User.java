package org.example.sample.users.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RegisterForReflection
@Slf4j
public class User {

    public static final String USERS_TABLE = "Users";
    public static final String USERNAME_ATTR= "username";
    public static final String CREATED_DATE_ATTR = "createdDate";
    public static final String COUNTRY_CODE_ATTR = "countryCode";

    private String username;
    private Date createdDate;
    private String countryCode;

}