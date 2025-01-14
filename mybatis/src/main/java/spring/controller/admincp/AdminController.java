package spring.controller.admincp;

import spring.mapper.DepartMapper;
import spring.mapper.EmployeeMapper;
import spring.model.admin.Department;
import spring.model.admin.Employee;
import spring.mapper.UserMapper;
import spring.model.admin.UserAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;



@Controller
@RequestMapping("/admincp")
public class AdminController
{
    @Autowired
    UserMapper userMapper;
    @Autowired
    DepartMapper departMapper;
    @Autowired
    EmployeeMapper empMapper;

//    @RequestMapping(value = "/")
    public String goToAdmin(ModelMap model)
    {
        List<Department> list = departMapper.getAllDept();
        Map map = Department.castList2Map(list);
        model.addAttribute("listDeparts", list);
        model.addAttribute("departments", map);
        return "admin/welcome";
    }

    @RequestMapping(value = {"/login"})
    public String goToLogin(ModelMap model)
    {
        return "admin/login";
    }

    @RequestMapping(value = {"/"}, method=RequestMethod.POST)
    public String login(ModelMap model
    , @RequestParam("email") String user
    , @RequestParam("password") String pass)
    {
        UserAdmin userAdmin = userMapper.getUser(user);
        if (userAdmin.getPassword().equals(pass))
        {
            model.addAttribute("adminName", userAdmin.getFullName());
            return goToAdmin(model);
        }
        return goToLogin(model);
    }

}
