package com.lemon.security.controller;

import com.jayway.jsonpath.JsonPath;
import com.lemon.security.springBootApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = springBootApplication.class)
public class UsetrController {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    @Before
    public void init(){
        mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void test1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user1")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(4));

    }
    @Test
    public void test2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("username","我是一个name"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }
    @Test
    public void test3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user3/qwe")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }
    @Test
    public void test4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user4")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()")
                        .value(3));
    }
    @Test
    public void test5() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/getInfo/1")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.
                        jsonPath("$.username").value("lemon"));
    }
    @Test
    public void test6() throws Exception{
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/getSimpleUser")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);

    }
    @Test
    public void test7() throws Exception{
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/getDetailUser")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }
    @Test
    public void test8() throws Exception{
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post("/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .param("username", "tom")
                .param("password", "123456"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }
    @Test
    public void test9() throws Exception {
        String content = "{\"username\":\"lemon\",\"password\":\"123456\"}";
        String a ="{\"username:\"lemon,\"password:\"123455\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/createUser")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));
    }
    @Test
    public void test10() throws Exception{
        //\"username":"null","password":"null"
        String context = "{\"username\":null,\"password\":null}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user2")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(context))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"));

    }
    @Test
    public void test11() throws Exception{
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);
    }
    @Test
    public void test12() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void test13() throws Exception{
        Date date = new Date(LocalDateTime.now().plusYears(1)
        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String context = "{\"username\":null,\"password\":null,\"birthday\":"+date.getTime()+"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(context))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"));

    }
    @Test
    public void test14() throws Exception {
        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String content = "{\"username\":null,\"password\":null,\"birthday\":" + date.getTime() + ",\"idCard\":\"411328199302055033\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user4")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("4"));
    }
}
