package com.digital.userprofile.pojo.requestmodel;

import lombok.Getter;

@Getter
public enum ErrorCode {
    BG2000("BG2000", "User Not found."),
    BG2001("BG2001", "Invalid token."),
    ;
    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}