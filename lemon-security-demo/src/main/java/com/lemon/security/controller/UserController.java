package com.lemon.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lemon.security.dao.User;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private List<User> generateUsers() {
            List<User> users = new ArrayList<>();
            users.add(new User());
            users.add(new User());
            users.add(new User());
            return users;
    }
    @RequestMapping(value = "/user1",method = RequestMethod.GET)
    public List<User> query1(){
        return generateUsers();
    }
    @GetMapping(value = "/user2")
    public List<User> query2(@RequestParam String username){
        System.out.println("RequestParam"+username);
        return generateUsers();
    }
    @GetMapping(value = "/user3/{username}")
    public List<User> query3(@PathVariable String username){
        System.out.println("PathVariable"+username);
        return generateUsers();
    }
    @GetMapping(value = "/user4")
    public List<User> query4(@PageableDefault(page = 1,size = 2,sort = "username") Pageable pageable){
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());

        return generateUsers();
    }
    @GetMapping("/getInfo/{id:\\d+}")
    public User getInfo(@PathVariable String id){
        System.out.println("查询的对象ID为：".concat(id));
        User user = new User();
        user.setUsername("lemon");
        return user;
    }
    @GetMapping("/getSimpleUser")
    @JsonView(User.UserSimpleView.class)
    public User getSimpleUser(){
        User user = new User();
        user.setUsername("lemon");
        user.setPassword("123456");
        return user;
    }
    @GetMapping("/getDetailUser")
    @JsonView(User.UserDetailView.class)
    public User getDetailUser(){
        User user = new User("lemon","123456");
        return user;
    }
    //用户创建请求post
    @PostMapping("/createUser")
    public User create(@RequestBody User user){
        System.out.println(ReflectionToStringBuilder.reflectionToString(user,ToStringStyle.MULTI_LINE_STYLE));
        user.setId("2");
        return user;
    }
    //@valid和BindingResult
    @PostMapping("/user2")
    public User create2(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(ReflectionToStringBuilder.reflectionToString(user,ToStringStyle.MULTI_LINE_STYLE));
        user.setId("2");
        return user;
    }
    @PutMapping("/user/{id:\\d+}")
    public User update(@PathVariable String id){
        User user = new User();
        user.setId(id);
        System.out.println("模拟修改："+id);
        user.setUsername("lemon");
        return user;
    }
    @DeleteMapping("/user/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println("模拟删除："+id);
    }
    @PostMapping("/user4")
    public User create4(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        user.setId("4");
        return user;
    }

}
