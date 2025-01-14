package spring.controller;

import spring.mapper.DepartMapper;
import spring.model.admin.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Map;

@Controller
public class LoginController
{
    @Autowired
    DepartMapper departMapper;
    @RequestMapping(value = {"/index"})
    public String index(ModelMap modelMap)
    {
        List<Department> list = departMapper.getAllDept();
        modelMap.addAttribute("departments", Department.castList2Map(list));
        return "welcome";
    }
    @RequestMapping(value = {"/login"})
    public String loginPage()
    {
        return "login";
    }
    @RequestMapping(value = {"/"} )
    public String login( @RequestParam("email") String email
         , @RequestParam("password") String pass
         , ModelMap model)
    {
        return "forward:/index";
    }
}