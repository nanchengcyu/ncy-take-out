package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName: UserMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author 南城余
 * @Create 2023/12/28 20:55
 * @Version 1.0
 */
@Mapper
public interface UserMapper  {

    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);//此处为User类型是因为Impl处需要返回一个User对象

    void insert(User user);
}
