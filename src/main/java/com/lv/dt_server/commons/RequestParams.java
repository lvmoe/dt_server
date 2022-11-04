package com.lv.dt_server.commons;

import lombok.Data;

@Data
public class RequestParams {
    private String userName;
    private String password;
    private String token;
    private String name;
    private String avatar;
}
