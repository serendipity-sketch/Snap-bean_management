package com.example.bean.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bean.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemMapper extends BaseMapper<User> {}

