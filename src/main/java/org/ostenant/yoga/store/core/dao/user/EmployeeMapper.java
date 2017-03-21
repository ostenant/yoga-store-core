package org.ostenant.yoga.store.core.dao.user;

import org.ostenant.yoga.store.core.bean.user.Employee;

public interface EmployeeMapper {
    int deleteByPrimaryKey(String username);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}