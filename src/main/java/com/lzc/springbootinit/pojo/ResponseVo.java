package com.lzc.springbootinit.pojo;

import lombok.Data;

@Data
public class ResponseVo {

    Integer code;

    String msg;

    Object data;

    public ResponseVo(int i, String msg, Object data) {
        this.code = i;
        this.msg = msg;
        this.data = data;
    }
    public static ResponseVo success() {
        return new ResponseVo(200, "success", null);
    }

    public static ResponseVo success(Object data) {
        return new ResponseVo(200, "success", data);
    }

    public static ResponseVo error(Object data) {
        return new ResponseVo(500, "error", data);
    }
}
