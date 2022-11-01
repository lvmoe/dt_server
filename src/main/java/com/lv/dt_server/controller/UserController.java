package com.lv.dt_server.controller;

import com.lv.dt_server.commons.RequestParams;
import com.lv.dt_server.commons.ResponseResult;
import com.lv.dt_server.commons.Result;
import com.lv.dt_server.dao.UserRepository;
import com.lv.dt_server.entiy.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ResponseResult
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final HttpServletRequest request;
    private final UserRepository userRepository;

    public UserController(HttpServletRequest request, UserRepository userRepository) {
        this.request = request;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/login")
    public Object login(@RequestBody RequestParams requestParams) {
        log.info(request.getRequestURI());
        User user = userRepository.findUserByUserNameAndPassword(requestParams.getUsername(), requestParams.getPassword());
        if (user == null) {
            return Result.fail(null, 50000, "登录失败");
        }
        Map<String, Object> map = new HashMap<>();
        //TODO token
        map.put("token", requestParams.getUsername() + requestParams.getPassword());
        return map;
    }

    @GetMapping(path = "/info")
    public Object info(@RequestParam String token) {
        log.info(request.getRequestURI());
        log.info(token);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "小天");
        return map;
    }

    @PostMapping(path = "/logout")
    public void logout() {
        log.info(request.getRequestURI());
    }
}
