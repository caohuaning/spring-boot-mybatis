package com.chn.modules.sys.mapper;

import com.chn.modules.sys.bean.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface UserMapper {
    User findByUsername(@Param(value = "username") String username);
}
