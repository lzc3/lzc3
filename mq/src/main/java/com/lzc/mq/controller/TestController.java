package com.lzc.mq.controller;

import com.lzc.mq.publisher.PublishMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试接口")
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final PublishMessageService publishMessageService;


    @ApiOperation("发送消息")
    @GetMapping("/pushMessageByType")
    public void publishMessage(@RequestParam(value = "type") String type) {
        publishMessageService.publishMessageByType(type);
    }
}
