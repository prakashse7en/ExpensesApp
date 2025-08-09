package com.digital.userprofile.pojo.requestmodel;

import lombok.*;

import java.io.Serializable;

@Data
public class UserRequestModel implements Serializable {

    private String userId;
    private String userName;
    @ToString.Exclude
    private String userPhoneNumber;
    @ToString.Exclude
    private String userEmail;

}
