package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: DishFlavorMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author 南城余
 * @Create 2023/12/24 21:16
 * @Version 1.0
 */
@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入口味
     * @param flavors
     */


    void insertBatch(List<DishFlavor> flavors);
}
