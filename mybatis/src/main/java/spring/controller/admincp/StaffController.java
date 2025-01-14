package spring.controller.admincp;

import spring.mapper.DepartMapper;
import spring.mapper.EmployeeMapper;
import spring.model.admin.Department;
import spring.model.admin.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Provider;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admincp")
public class StaffController
{
    @Autowired
    EmployeeMapper empMapper;
    @Autowired
    DepartMapper deptMapper;

    //region chức năng xóa staff
    @RequestMapping(value = {"/dropStaff"} , method = RequestMethod.GET)
    public String dropForm(ModelMap model, @RequestParam("staffNo") int staffNo)
    {
        model.addAttribute("staffNo", staffNo);
        return "admin/dropStaff";
    }
    @RequestMapping(value = {"/dropStaff"} , method = RequestMethod.POST)
    public String dropStaff(ModelMap model
    , @RequestParam("staffNo") int staffNo
    , @RequestParam("userPass") String adminPass
    , RedirectAttributes redirect)
    {
        try {
            empMapper.drop1StaffByNo(staffNo);
        } catch (Exception ex) {}

        redirect.addFlashAttribute("successMessage", "Success !");
        return "redirect:/admincp/allStaff";
    }
    //endregion chức năng xóa staff

    //region view lưới staff
    @RequestMapping(value = {"/allStaff"})
    public String allStaffsPage(ModelMap model)
    {
        List<Employee> listStaffs = empMapper.getAllStaffs();
        List<Department> listDepts = deptMapper.getAllDept();
        model.addAttribute("listStaffs", listStaffs);
        model.addAttribute("listDepts", listDepts);
        model.addAttribute("departments", Department.castList2Map(listDepts));
        return "admin/staffs";
    }

    @RequestMapping(value = {"/staff"} )
    public String staffSearch(ModelMap model
    , @RequestParam("empName") String empName
    , @RequestParam("deptNo") int deptId  )
    {
        List<Employee> listStaffs;
        List<Department> listDepts = deptMapper.getAllDept();
        model.addAttribute("listDepts", listDepts);
        if (empName.equals("") && deptId<0)
        {
            listStaffs = empMapper.getAllStaffs();
            model.addAttribute("listStaffs", listStaffs);
            return "admin/staffs";
        }
        if (empName.equals("") || deptId<0)
        {
            if (empName.equals(""))
            {
                listStaffs = empMapper.getStaffsListForDept(deptId);
                model.addAttribute("listStaffs", listStaffs);
            }
            if (deptId<0)
            {
                listStaffs = empMapper.getStaffsListForName(empName);
                model.addAttribute("listStaffs", listStaffs);
            }
        }   else {
            listStaffs = empMapper.searchStaffsList(empName, deptId);
            model.addAttribute("listStaffs", listStaffs);
        }
        return "admin/staffs";
    }
    //endregion view lưới staff

    //region chức năng thêm/sửa
    @RequestMapping(value = {"/addStaff"} )
    public String newStaffForm(ModelMap model) // đi tới form tạo mới
    {
        List<Department> departments = deptMapper.getAllDept();
        model.addAttribute("deptList", departments);
        model.addAttribute("employee", new Employee());
        model.addAttribute("mode", 1); //mode hiện tại là thêm mới
        return "admin/staffDetail";
    }

    @RequestMapping(value = {"/staffDetail"} )
    public String detailStaffForm(ModelMap model
         , @RequestParam("mode") int mode
         , @RequestParam("staffNo") int staffNo)
    {
        List<Department> deptList = deptMapper.getAllDept();
        model.addAttribute("deptList", deptList);
        model.addAttribute("mode", mode);
        model.addAttribute("staffNo", staffNo);
        model.addAttribute("staffInfor", empMapper.get1StaffByNo(staffNo));
        model.addAttribute("departments", Department.castList2Map(deptList));
        return "admin/StaffDetail";
    }
    @RequestMapping(value = {"/staffDetail"} , method = RequestMethod.POST)  // đi tới form sửa
    public String editStaffForm(ModelMap model
            , @RequestParam("mode") int mode
            , @RequestParam("staffNo") int staffNo )
    {
        String prepareMap = detailStaffForm(model,mode,staffNo);
        return "admin/staffDetail";
    }

    @RequestMapping(value = {"/staffSuccess"} , method = RequestMethod.POST)
    public String success(ModelMap model
         , @ModelAttribute Employee staff
         , @RequestParam("mode") int mode
         , RedirectAttributes redirect)
    {
        if(mode == 1)   empMapper.addNewStaff(staff);
        else if (mode == -1) empMapper.updateStaff(staff);
            else empMapper.get1StaffByNo(staff.getId());
        redirect.addFlashAttribute("successMessage", "Success !");
        return "redirect:/admincp/allStaff";
    }
    //endregion chức năng thêm/sửa
}
