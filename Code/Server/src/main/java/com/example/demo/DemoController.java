package com.example.demo;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @PostMapping("/login")
    public String login(String name) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setMsg("OK");
        StringBuffer buffer = new StringBuffer(name == null ? "你的名字呢？" : name);
        baseResponse.setData(buffer.reverse().toString());
        return new Gson().toJson(baseResponse);
    }
}
