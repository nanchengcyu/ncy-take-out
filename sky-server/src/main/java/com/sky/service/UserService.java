package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * ClassName: UserService
 * Package: com.sky.service
 * Description:
 *
 * @Author 南城余
 * @Create 2023/12/28 20:09
 * @Version 1.0
 */
public interface UserService {

    User wxLogin(UserLoginDTO userLoginDTO);
}
