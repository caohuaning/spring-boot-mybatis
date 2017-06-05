package com.chn.modules.sys.service;

import com.chn.modules.sys.bean.User;
import com.chn.modules.sys.mapper.RoleMapper;
import com.chn.modules.sys.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    /**
     * 重写spring security loadUserByUsername() 用于登录
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("用户名为空");
        }
        User user = this.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("用户名不存在"));
        user.setRoleList(roleService.getRoles(user.getId()));
        return user;
    }


    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userMapper.findByUsername(username));
    }
}
