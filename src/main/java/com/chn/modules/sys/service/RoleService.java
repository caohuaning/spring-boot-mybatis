package com.chn.modules.sys.service;

import com.chn.modules.sys.bean.Role;
import com.chn.modules.sys.mapper.RoleMapper;
import com.chn.modules.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {


    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户id获取用户角色
     *
     * @param userId
     * @return
     */
    public List<Role> getRoles(String userId) {
        return roleMapper.getRoles(userId);
    }
}
