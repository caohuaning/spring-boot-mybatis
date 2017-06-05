package com.chn.modules.sys.bean;

import com.chn.common.persistence.BaseEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * 用户Entity
 */
@Data
public class User extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 1L;
    /**
     * 登录名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 工号
     */
    private String no;
    /**
     * 姓名
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 最后登陆IP
     */
    private String loginIp;
    /**
     * 最后登陆日期
     */
    private Date loginDate;
    /**
     * 是否允许登陆
     */
    private String loginFlag;
    /**
     * 头像
     */
    private String photo;

    /**
     * 拥有角色列表
     */
    private List<Role> roleList = Collections.emptyList();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        this.getRoleList().stream().forEach(role -> authorityList.add(new SimpleGrantedAuthority(role.getName())));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}