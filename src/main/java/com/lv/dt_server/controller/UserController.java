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
}
