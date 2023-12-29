package com.sky.mapper;

import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * ClassName: OrderDetailMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author 南城余
 * @Create 2023/12/29 21:58
 * @Version 1.0
 */
@Mapper
public interface OrderDetailMapper {

    void insertBatch(ArrayList<OrderDetail> orderDetailList);
}
