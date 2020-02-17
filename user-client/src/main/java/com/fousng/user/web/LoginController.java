package com.fousng.user.web;

import com.alibaba.fastjson.JSON;
import com.fousng.user.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app/cloud")
public class LoginController {

    @PostMapping("/login")
    public Map<String, Object> login (@RequestBody UserDto userDto) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (userDto == null || userDto.getUserName() == null) {

            resultMap.put("msg", "登陆失败");
        }


        if (userDto.getUserName().equals("admin") && userDto.getPwd().equals("123qwe")) {
            resultMap.put("data", userDto);
            resultMap.put("message", "登陆成功");
            resultMap.put("code", "200");
        } else {

            resultMap.put("message", "登陆失败");
            resultMap.put("code", "500");
        }

        return resultMap;

    }

    @GetMapping("/userInfo")
    public Map<String, Object> userInfo(@RequestParam("userId")String userId){
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("userId", userId);
        resultMap.put("userName", "张三");
        resultMap.put("telephone", "13980928761");
        resultMap.put("email", "zhangsan@163.com");
        resultMap.put("auths", "select,update,insert,delete");

        return resultMap;
    }

}
