package com.lv.dt_server.controller;

import com.lv.dt_server.commons.*;
import com.lv.dt_server.dao.UserRepository;
import com.lv.dt_server.entiy.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ResponseResult
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final HttpServletRequest request;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private UserRepository userRepository;

    @Resource
    private JwtUtils jwtUtils;

    public UserController(HttpServletRequest request) {
        this.request = request;
    }

    @PostMapping(path = "/login")
    public Object login(@RequestBody RequestParams requestParams) {
        log.info(request.getRequestURI());
        User user = userRepository.findUserByUserNameAndPassword(requestParams.getUsername(), requestParams.getPassword());
        if (user == null) {
            return Result.fail(null, 50000, "登录失败");
        }
        String token = jwtUtils.generateToken(user.getUserName());
        redisUtils.set(requestParams.getUsername(), token);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
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
    public Object logout() {
        log.info(request.getRequestURI());
        String userName = request.getAttribute("userName").toString();
        log.info(userName);
        if (!redisUtils.delete(userName)) {
            return Result.fail(null, 50000, "登出失败");
        }
        return Result.success("登出成功");
    }
}
