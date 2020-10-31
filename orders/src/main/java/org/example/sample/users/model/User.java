package org.example.sample.users.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
public class User {

    public User(){
    }

    public User(String defaultUsername){
        this.username = defaultUsername;
    }

    private String username;
    private Date createdDate;
    private String countryCode;

}
