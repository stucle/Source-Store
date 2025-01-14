package spring.mapper;

import spring.model.admin.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmployeeMapper
{
    final String QUERY_ALL_STAFF = " SELECT s.Id , s.Name , s.Gender , s.Birthday , s.Photo , s.DepartId , s.Email , s.Level , s.Phone , s.Salary, s.Notes , s.DepartId , d.Name as 'DepartName' "
            + " FROM staffs s  join departs d on s.DepartId = d.Id ";
    @Select(QUERY_ALL_STAFF + " ORDER BY s.DepartId;  ")
    List<Employee> getAllStaffs();

    @Select(QUERY_ALL_STAFF + " WHERE s.DepartId = #{deptId}; ")
    List<Employee> getStaffsListForDept(int deptId);
    @Select(QUERY_ALL_STAFF + " WHERE s.Name LIKE CONCAT('%',#{staffName},'%'); ")
    List<Employee> getStaffsListForName(String staffName);

    final String GET_SEARCH_STAFF = QUERY_ALL_STAFF
        + " WHERE s.DepartId = #{deptId} AND s.Name LIKE CONCAT('%',#{staffName},'%');";
    @Select(GET_SEARCH_STAFF)
    List<Employee> searchStaffsList(@Param("staffName") String staffName, @Param("deptId") int deptId);

    @Select(" SELECT * FROM Staffs s WHERE s.Id = #{staffNo} ;")
    Employee get1StaffByNo(int staffNo);
    @Delete(" DELETE FROM Staffs WHERE Id = #{staffNo} ;")
    boolean drop1StaffByNo(int staffNo);
//    @Update(" UPDATE Staffs() WHERE Id = #{staffNo} ")
//    boolean editStaffsByNo(Employee staff);
    final String INSERT_STAFF_COMMON = " INSERT INTO staffs ( Name, Gender, Birthday, Photo, Email, Phone, Salary, Level, Notes, DepartId) VALUES ";
    final String ADD_STAFF = INSERT_STAFF_COMMON
            + "( #{name} , #{gender} , #{birthday} , #{photo} , #{email} , #{phone} , #{salary} , #{level} , #{notes} , #{departId} ); ";
    @Update(ADD_STAFF)
    void addNewStaff(Employee staff);

    final String UPDATE_STAFF = " UPDATE Staffs s SET s.Name = #{name} ,  s.Gender = #{gender} , s.Birthday = #{birthday} , s.Photo = #{photo} " +
            " , s.DepartId = #{departId} , s.Email = #{email} , s.Level = #{level} , s.Notes = #{notes} ";
    final String UPDATE_CONDITION = " WHERE Id = #{id} ; ";
    boolean updateStaff(Employee staff);
}
