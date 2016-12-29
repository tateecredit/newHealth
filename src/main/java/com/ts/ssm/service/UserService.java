package com.ts.ssm.service;

import com.ts.ssm.model.User;

import java.util.List;

/**
 * Created by ts on 2016/12/23.
 */
public interface UserService {

    List<User> getAllUser();

    User getUserByPhoneOrEmail(String emailOrPhone, Short state);

    User getUserById(Long userId);
}
