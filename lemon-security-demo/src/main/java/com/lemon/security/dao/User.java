package com.lemon.security.dao;

import com.fasterxml.jackson.annotation.JsonView;
import com.lemon.security.validate.IsIdCard;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class User {
    private String id;
    @NotEmpty(message = "用户名不能为空！")
    private String username;
    @NotEmpty(message = "密码不能为空！")
    private String password;
    @Past(message = "生日必须是过去时间")
    private Date birthday;
    @IsIdCard(message = "身份证号不正确！")
    private String idCard;
    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{};

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
