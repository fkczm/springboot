package com.abc.demo.easyExcle.mapper;

import com.abc.demo.easyExcle.domain.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeMapper  extends BaseMapper<Employee> {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);
    int insertBatchEmployee(List<Employee> records);

    Employee selectByPrimaryKey(Integer id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
}