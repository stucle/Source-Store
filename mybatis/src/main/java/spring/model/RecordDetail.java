package spring.model;

import spring.model.admin.Record;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordDetail extends Record
{
    int recordNo, staffNo, departNo;
    boolean type;
    Date date;
    String reason, staffName, email, department;

    //region các hàm tạo()
    public RecordDetail() {
        recordNo = super.id;
        staffNo = super.staffId;
        date = super.date;
        type = super.type;
        reason = super.reason;
    }
    public RecordDetail(int recordNo)
    {
        this.recordNo = recordNo;
    }
    public RecordDetail(int recordNo, boolean type, String reason, Date date, int staffNo, String email)
    {
        this.recordNo = recordNo;
        this.staffNo = staffNo;
        this.type = type;
        this.reason = reason;
        this.date = date;
        this.email = email;
    }
    //endregion

    //region getter&setter

    public int getRecordNo() { return recordNo; }
    public void setRecordNo(int recordNo) { this.recordNo = recordNo; }

    public int getStaffNo() { return staffNo; }
    public void setStaffNo(int staffNo) { this.staffNo = staffNo; }

    public int getDepartNo() { return departNo; }
    public void setDepartNo(int departNo) { this.departNo = departNo; }

    public boolean getType() { return type; }
    public void setType(boolean type) { this.type = type; }

    @Override
    public Date getDate() { return this.date; }
    @Override
    public void setDate(Date date) { this.date = date; }

    @Override
    public String getReason() { return this.reason; }
    @Override
    public void setReason(String reason) { this.reason = reason; }

    public String getStaffName() { return staffName; }
    public void setStaffName(String staffName) { this.staffName = staffName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    //endregion

    public static ArrayList<RecordDetail> parseRecordsToList(ResultSet dataLine)
            throws SQLException
    {
        ArrayList<RecordDetail> list = new ArrayList<RecordDetail>();
        while (dataLine.next())
        {
            int recordNo = dataLine.getInt("RecordNo");
            boolean type = dataLine.getBoolean("Type");
            String reason = dataLine.getString("Reason");
            Date date = dataLine.getDate("Date");
            int staffNo = dataLine.getInt("DepartNo");
            String staffName = dataLine.getString("StaffName");
            String eMail = dataLine.getString("Email");
            int departNo = dataLine.getInt("DepartNo");
            String departName = dataLine.getString("Department");
            RecordDetail record = new RecordDetail(recordNo, type, reason, date, staffNo, eMail);
            record.setStaffName(staffName);
            record.setDepartNo(departNo);
            record.setDepartment(departName);
            list.add(record);
        } //dataLine.close();
        return list;
    }

    public static Map castList2Map(List<RecordDetail> list)
    {
        Map<Integer, RecordDetail> map = new HashMap<>();
        for (RecordDetail record : list)
        {
            map.put(record.getRecordNo(), record);
        }
        return map;
    }
}
