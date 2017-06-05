package com.chn.modules.sys.mapper;

import com.chn.modules.sys.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<Role> getRoles(@Param(value = "userId") String userId);
}
