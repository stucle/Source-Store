package spring.model.admin;

import spring.model.RecordDetail;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Record
{
    protected int id, staffId;
    protected boolean type;
    protected String reason;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date date;

    //region hàm tạo()
    public Record() {}
    public Record(int rID) {
        this.id = rID;
    }
    public Record(RecordDetail recordDetail)
    {
        this.id = recordDetail.getRecordNo();
        this.type = recordDetail.getType();
        this.reason = recordDetail.getReason();
        this.date = recordDetail.getDate();
        this.staffId = recordDetail.getStaffNo();
    }
    public Record(int rID, boolean type, String reason, Date date, int staffId)
    {
        this.id = rID;
        this.staffId = staffId;
        this.type = type;
        this.reason = reason;
        this.date = date;
    }
    public Record(boolean type, String reason, Date date, int staffId)
    {
        this.staffId = staffId;
        this.type = type;
        this.reason = reason;
        this.date = date;
    }
    //endregion hàm tạo
    //region getter setter
    public int getId() { return id; }

    public void setId(int id) { this.id = id;}

    public int getStaffId() { return staffId;  }

    public void setStaffId(int staffId) { this.staffId = staffId; }

    public boolean getType() { return type; }

    public void setType(boolean type) { this.type = type;  }

    public String getReason() {   return reason;  }

    public void setReason(String reason) {   this.reason = reason;  }

    public Date getDate() {  return date;  }

    public void setDate(Date date) {  this.date = date;  }
    //endregion getter setter

    Map<Integer, Record> castList2Map(List<Record> list)
    {
        Map<Integer, Record> map = new HashMap<>();
        for (Record record : list)
        {
            map.put(record.id, record);
        }
        return map;
    }
}
