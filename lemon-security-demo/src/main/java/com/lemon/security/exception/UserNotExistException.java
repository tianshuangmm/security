package com.lemon.security.exception;


public class UserNotExistException extends Throwable {
    private Integer id;

    public UserNotExistException(Integer id) {
        super("user not exist.");
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
