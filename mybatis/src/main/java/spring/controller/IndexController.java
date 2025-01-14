package spring.controller;

import spring.mapper.RecordMapper;
import spring.model.RecordDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController
{
    @Autowired
    RecordMapper recordMapper;

    @RequestMapping(value = {"info"})
    public String inforPage(ModelMap modelMap)
    {
        Map records = RecordDetail.castList2Map(recordMapper.getAllSortByDept());
        modelMap.addAttribute("listRecords", records);
        return "inforPage";
    }
}
