package com.example.bean.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bean.common.Result;
import com.example.bean.entity.User;
import com.example.bean.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper; // 注入接口

    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        wrapper.eq("password", user.getPassword());

        // selectOne 是 BaseMapper 自带的方法，直接调用即可
        User res = userMapper.selectOne(wrapper);

        if (res == null) return Result.error("用户名或密码错误");
        return Result.success(res);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());

        // selectCount 也是自带的
        if (userMapper.selectCount(wrapper) > 0) {
            return Result.error("用户名已存在");
        }

        user.setRole("USER");
        user.setCreateTime(LocalDateTime.now()); // 设置创建时间
        // insert 也是自带的
        userMapper.insert(user);
        return Result.success(null);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody User user) {
        // updateById 也是自带的
        userMapper.updateById(user);
        return Result.success(null);
    }
}