package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * ClassName: UserServiceImpl
 * Package: com.sky.service.impl
 * Description:
 *
 * @Author 南城余
 * @Create 2023/12/28 20:09
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    //微信服务接口地址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserMapper userMapper;

    public User wxlogin(UserLoginDTO userLoginDTO) {
        //1.调用微信接口服务，获取当前登录用户的openid
       //此处代码抽取到下方方法中
        String openid = getOpenid(userLoginDTO.getCode());


        //2.判断openid是否为空，如果为空表示登录失败 抛出业务异常
        if (openid == null ){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        //3.判断用户是否为新用户
        User user = userMapper.getByOpenid(openid);
        //4.如果是新用户自动完成注册
        if (user == null){
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
        userMapper.insert(user);
        }

        //5.返回这个用户对象


        return user;
    }
    private String getOpenid(String code) {
        //调用微信接口服务，获取当前微信用户的openid
        HashMap<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        //此处上面四个参数也是map中要放入的，也是微信官方文档指出要传入的四个必要参数
        String json = HttpClientUtil.doGet(WX_LOGIN_URL, map);//这里调用get方法是因为微信官方文档指出为get方法，而放入的两个参数可以ctrl+p即可知道
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }
}
