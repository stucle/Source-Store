package spring.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

import spring.model.admin.Department;
import org.apache.ibatis.annotations.Update;

public interface DepartMapper
{
    final String GET_ALL_DEPT_CLAUSE = " SELECT * FROM Departs ";
    @Select(GET_ALL_DEPT_CLAUSE)
    List<Department> getAllDept();
    @Select(GET_ALL_DEPT_CLAUSE + " WHERE Departs.id = #{deptNo} ")
    Department getDeptByNo(int deptNo);
    @Update(" UPDATE Depart SET Name=#{name} WHERE Id=#{id}; ")
    boolean editDeptName(Department department);
    @Insert(" INSERT INTO Depart(Id, Name) VALUES(#{id} , #{name}); ")
    boolean addNewDept(Department department);
}
