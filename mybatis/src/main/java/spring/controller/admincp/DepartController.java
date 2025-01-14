package spring.controller.admincp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.mapper.DepartMapper;
import spring.mapper.RecordMapper;
import spring.model.CommonDbHelper;
import spring.model.admin.Department;
import spring.model.admin.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DepartController {

    @Autowired
    DepartMapper departMapper;
    @Autowired
    RecordMapper recordMapper;

    @RequestMapping(value = {"/admincp/viewGlobalDepart"})
    public String departGeneralStatistic(ModelMap modelMap)
    {
        modelMap.addAttribute("pr_deptMapper", departMapper);
        modelMap.addAttribute("pr_rcMapper", recordMapper);
        DepartHelper.prepareListDept(modelMap);
        return "admin/departStatistic";
    }

    public static class DepartHelper    {

        //mệnh đề from lấy các staff có mặt trong bảng record
        public static final String _FROM_STRING_ = " from staffs s where s.Id IN ( select StaffNo as 'Id' from recorddetails r ) ";

        public static void prepareListDept(ModelMap modelMap)
        {
            DepartMapper deptMapper =(DepartMapper) modelMap.get("pr_deptMapper");
            RecordMapper recordMapper =(RecordMapper) modelMap.get("pr_rcMapper");
            List<Department> depts = deptMapper.getAllDept();
            List<ModelMap> ddList = new ArrayList<>();
            Connection connection = null; PreparedStatement statement = null; ResultSet resultSet = null;
            try { connection = CommonDbHelper.getMySQLConnection(); }
            catch (SQLException e) { e.printStackTrace(); }
            catch (ClassNotFoundException e) { e.printStackTrace(); }

            for (Department dept : depts)
            {
                ModelMap deptDetail = new ModelMap();
                deptDetail.addAttribute("deptNo", dept.getId());
                deptDetail.addAttribute("deptName", dept.getName());
                try {
                    String empHaveRecordByDept = "select s.* " + _FROM_STRING_
                            + " and DepartId = ? ";
                    statement = connection.prepareStatement(empHaveRecordByDept);
                    statement.setInt(1, dept.getId());
                    resultSet = statement.executeQuery();
                    deptDetail.addAttribute("empList", Employee.parse2List(resultSet));
                    deptDetail.addAttribute("laudRecords", recordMapper.countRecordByDept(true, dept.getId()));
                    deptDetail.addAttribute("critRecords", recordMapper.countRecordByDept(true, dept.getId()));
                    resultSet.close(); statement.close();
                    //
                    statement = connection.prepareStatement("call proc_count_year(?, ?);");
                    statement.setBoolean(1, true);
                    statement.setInt(2, dept.getId());
                    resultSet.close(); statement.close(); connection.close();
                } catch (SQLException e) { e.printStackTrace(); }
                ddList.add(deptDetail);
            }
            modelMap.addAttribute("departments", ddList);
        }
    }
    //end class
}
