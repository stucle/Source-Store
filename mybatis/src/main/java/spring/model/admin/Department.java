package spring.model.admin;

import java.util.*;

public class Department
{
    int id; String name;
    //region Hàm Tạo()
    public Department() { }
    public Department(String name) { this.name = name; }
    //endregion

    //region getter&setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    //endregion

    public static Map<Integer , String> castList2Map(List<Department> deptList) //ép List dept thành Map dept
    {
        Map<Integer , String> map = new HashMap<>();
        int n = deptList.size();
        for (Department i : deptList)
        {
            map.put(i.getId(), i.getName());
        }
        return map;
    }
}
