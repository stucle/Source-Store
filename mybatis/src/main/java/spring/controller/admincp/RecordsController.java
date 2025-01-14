package spring.controller.admincp;

import spring.mapper.DepartMapper;
import spring.mapper.EmployeeMapper;
import spring.mapper.RecordMapper;
import spring.model.RecordHelper;
import spring.model.admin.Employee;
import spring.model.admin.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import spring.model.RecordDetail;

@Controller
public class RecordsController
{
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    DepartMapper departMapper;
    @Autowired
    EmployeeMapper employeeMapper;

    @RequestMapping(value = "/admincp/allRecords")
    public String allRecords(ModelMap modelMap)
    {
        RecordHelper.groupAllRecords(modelMap, recordMapper, departMapper);
        return "admin/recordsForm";
    }

    @RequestMapping(value = "/admincp/records")
    public String filterRecordsByDept(ModelMap modelMap
    , @RequestParam("deptNo") int deptNo
    ) {
        RecordHelper.initModelMap(modelMap, departMapper);
        if(deptNo<0)
        {
            return allRecords(modelMap);
        }
        else {
            Map recordList = RecordDetail.castList2Map(recordMapper.getRecordsByDept(deptNo));
            modelMap.addAttribute("listRecords", recordList);
        }
        return "admin/recordsForm";
    }
    @RequestMapping(value = {"/admincp/records"} , method = RequestMethod.POST)
    public String initRecordsByStaff(ModelMap modelMap
    , @RequestParam("empNo") int staffNo
    )   {
        RecordHelper.initModelMap(modelMap, departMapper);
        modelMap.addAttribute("pr_StaffNo", staffNo); //lưu hành nội bộ: sử dụng key prefix "pr_"
        modelMap.addAttribute("pr_empMapper", employeeMapper);
        modelMap.addAttribute("pr_recordMapper", recordMapper);
        RecordHelper.groupByRecordMap(modelMap);
        modelMap.addAttribute("empDetail",  employeeMapper.get1StaffByNo(staffNo));
        return "admin/staffRecordsForm";
    }

    @RequestMapping(value = {"/admincp/addRecord"}, method = RequestMethod.POST)
    public String formAddRecord(ModelMap modelMap
//    , @RequestParam("mode") int mode
    , @ModelAttribute Employee staff
    )   {
        modelMap.addAttribute("pr_staffNo", staff.getId());
        modelMap.addAttribute("pr_deptMapper", departMapper);
        modelMap.addAttribute("pr_empMapper", employeeMapper);
        RecordHelper.prepareToNewRecord(modelMap);
        modelMap.addAttribute("mode", 1); //đặt mode new = 1 tức là Thêm
          return "admin/recordDetail";
    }
    @RequestMapping(value = {"/admincp/editRecord"} )
    public String formEditRecord(ModelMap modelMap
    , @RequestParam("recordNo") int recordNo
    )   {
        modelMap.addAttribute("recordLine", new Record(recordMapper.get1Record(recordNo)));
        modelMap.addAttribute("pr_deptMapper", departMapper);
        modelMap.addAttribute("pr_empMapper", employeeMapper);
        RecordHelper.prepareToModifyRecord(modelMap);
        modelMap.addAttribute("mode", 0); //đặt mode = 0 tức là Sửa
        return "admin/recordDetail";
    }
    @RequestMapping(value = {"/admincp/deleteRecord"} , method = RequestMethod.POST)
    public String deleteRecord(ModelMap modelMap
    , @RequestParam("recordNo") int recordNo
    )   {
        Record record = new Record(recordMapper.get1Record(recordNo));
        return updateSuccess(modelMap, record, -1);
    }
    @RequestMapping(value = {"/admincp/recordSuccess"}, method = RequestMethod.POST)
    public String updateSuccess(ModelMap modelMap
    , @ModelAttribute Record record
    , @RequestParam("mode") int mode
    )   {
        if (mode ==-1) {
            recordMapper.dropRecord(record.getId());
        }
        if (mode == 0) {
            recordMapper.editRecord(record);
        }
        if (mode == 1) {
            recordMapper.addRecord(record);
        }
        return allRecords(modelMap);
    }
}
