package com.javaqa.enums;

import lombok.Getter;

@Getter
public enum UserCredentials {

    STANDARD_USER("standard_user", "secret_sauce"),
    LOCKED_OUT_USER("locked_out_user", "secret_sauce"),
    PROBLEM_USER("problem_user", "secret_sauce"),
    PERFORMANCE_GLITCH_USER("performance_glitch_user", "secret_sauce"),
    NON_EXISTING_USER("QWERTY", "QWERTY");

    private final String userLogin;
    private final String userPass;

    UserCredentials(String userLogin, String userPass) {
        this.userLogin = userLogin;
        this.userPass = userPass;
    }
}
