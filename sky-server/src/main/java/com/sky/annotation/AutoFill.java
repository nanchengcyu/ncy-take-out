package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: AutoFill
 * Package: com.sky.annotation
 * Description:  自定义注解 用于标识某个方法需要进下功能字段自动填充
 *
 * @Author 南城余
 * @Create 2023/12/24 18:35
 * @Version 1.0
 */
@Target(ElementType.METHOD)//表示该注解只能用于方法上
@Retention(RetentionPolicy.RUNTIME)//表示此注解作用于运行时阶段
public @interface AutoFill {
     //数据库操作类型  UPDATE INSERT
    OperationType value(); //此类在package com.sky.enumeration;
}
