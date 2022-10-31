package com.lv.dt_server.controller;

import com.lv.dt_server.commons.RequestParams;
import com.lv.dt_server.commons.ResponseParams;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

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
