package com.lv.dt_server.controller;

import com.lv.dt_server.commons.RequestParams;
import com.lv.dt_server.commons.ResponseParams;
import com.lv.dt_server.commons.ResponseResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
@ResponseResult
public class UserController {

    @PostMapping(path = "/test")
    public Object test() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", "token");
        map.put("code", 20000);
        return map;
    }

    @ResponseBody
    @PostMapping(path = "/login")
    public ResponseParams login(@RequestBody RequestParams requestParams) {
        requestParams.setToken(requestParams.getUsername() + requestParams.getPassword());
        ResponseParams response = new ResponseParams();
        response.setData(requestParams);
        response.setCode(20000);
        return response;
    }

    @ResponseBody
    @GetMapping(path = "/info")
    public ResponseParams info(@RequestParam String token) {
        System.out.println(token);
        ResponseParams response = new ResponseParams();
        response.setCode(20000);
        RequestParams requestParams = new RequestParams();
        requestParams.setName("小天");
        requestParams.setAvatar("阿凡达");
        response.setData(requestParams);
        return response;
    }

    @ResponseBody
    @PostMapping(path = "/logout")
    public ResponseParams logout() {
        System.out.println("logout");
        ResponseParams response = new ResponseParams();
        response.setCode(20000);
        return response;
    }
}
