package com.chn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by caohuaning on 2017/5/25.
 */
@Configuration
public class PasswordEncoderConfig implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return null;
    }


    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) throws BadCredentialsException{
        return true;
    }
}
