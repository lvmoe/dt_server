package com.lv.dt_server.commons;

import lombok.Data;

@Data
public class ResponseParams {
    private RequestParams data;
    private Integer code;
}
