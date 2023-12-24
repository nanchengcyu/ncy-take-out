package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("员工退出")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增员工
     * @param employeeDTO
     * @return
     */
    @ApiOperation("新增员工")
    @PostMapping
    public Result save(@RequestBody EmployeeDTO employeeDTO){ //JSON数据要加上@RequestBody
        log.info("新增员工：{}",employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }
    @GetMapping("/page")
    @ApiOperation("员工分页查询") //查询操作时需要使用<>泛型封装对象
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){ //不是JSON数据不需要加上@RequestBody
      log.info("分页查询员工信息：{}",employeePageQueryDTO);
     PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
      return Result.success(pageResult);
    }

    /**
     * 员工账号状态变更
     * @param status
     * @param id
     * @return
     */
      //此处非查询操作所以不需要泛型操作

    @PostMapping("status/{status}")
    @ApiOperation("员工账号状态变更")
    public Result startOrStop(@PathVariable Integer status,Long id){//路径参数需要加@PathVariable
        log.info("员工账号状态变更：{},{}",status,id);
        employeeService.startOrStop(status,id);
        return Result.success();

    }

    /**
     *  根据ID查询员工信息
     * @param id
     * @return
     */

    @GetMapping("/{id}")   //查询操作需要封装到实体类 具体封装到哪个实体类主要看前端返回的数据类型
    @ApiOperation("根据id查询员工信息")
    public Result<Employee> getById(@PathVariable Long id){
        log.info("员工信息查询：{}",id);
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * 更改员工信息
     * @param employeeDTO
     * @return
     */
    @PutMapping
    public Result update(@RequestBody EmployeeDTO employeeDTO){
        log.info("更改员工信息：{}",employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }
}
