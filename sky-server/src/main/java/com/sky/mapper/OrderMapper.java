package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: OrderMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author 南城余
 * @Create 2023/12/29 21:58
 * @Version 1.0
 */
@Mapper
public interface OrderMapper {
     void insert(Orders orders);
}
