package spring.mapper;

import org.apache.ibatis.annotations.Param;
import spring.model.RecordDetail;
import spring.model.admin.Record;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface RecordMapper
{   //tất cả các query đều sort kết quả theo ngày
    @Select("select * from recorddetails ORDER BY Date DESC")
    List<RecordDetail> getAllRecords();
    @Select("SELECT * FROM recorddetails ORDER BY departNo ASC, Date DESC")
    List<RecordDetail> getAllSortByDept(); //lấy hết, sort theo DeptNo

    @Select("select * from recorddetails where RecordNo = #{recordNo} ")
    RecordDetail get1Record(int recordNo); //chỉ lấy 1 bản

    public static final String NORMAL = " not(Type is null) "; //điều kiện cơ bản lọc ra những record không bị lỗi
    @Select("SELECT * FROM recorddetails WHERE " +NORMAL+ " AND DepartNo = #{deptNo} ORDER BY Date DESC;")
    List<RecordDetail> getRecordsByDept(int deptNo); //lấy những bản có chung Dept
    @Select("SELECT COUNT(RecordNo) FROM recorddetails WHERE " +NORMAL+ " AND Type = #{type} AND DepartNo = #{deptNo} ")
    int countRecordByDept(@Param("type") boolean type , @Param("deptNo") int deptNo);

    @Select("SELECT * FROM recorddetails where " +NORMAL+ " AND DepartNo = #{deptNo} ORDER BY Date DESC limit 10")
    List<RecordDetail> get10SortByDept(int deptNo); //lấy 10 bản đầu tiên cùng Dept

    @Select("SELECT * FROM recorddetails WHERE " +NORMAL+ " AND StaffNo = #{staffNo} ORDER BY departNo ASC, Date DESC")
    List<RecordDetail> getRecordsByStaff(int staffNo); //lấy những bản có chung staff


    //region chức năng thêm xóa sửa
    public final String INSERT_COMMON = " INSERT INTO bookstore.records (Type, Reason, Date, StaffId) VALUES ";
    @Update(INSERT_COMMON + " (#{type}, #{reason}, #{date}, #{staffId}); ")
    boolean addRecord(Record record);

    public final String UPDATE_COMMON = " UPDATE bookstore.records t SET ";
    public final String TYPE_SET = " t.Type = #{type} ";
    public final String REASON_SET = " t.Reason = #{reason} ";
    public final String DATE_SET = " t.Date = #{date} ";
    public final String STAFF_SET = " t.StaffId = #{staffId} ";
    public final String FULL_SET = TYPE_SET + " , " + REASON_SET + " , " + DATE_SET + " , " + STAFF_SET;
    public final String UPDATE_CONDITION = " WHERE t.Id = #{id}; ";
    @Update(UPDATE_COMMON + FULL_SET + UPDATE_CONDITION)
    boolean editRecord(Record record);

    @Delete("DELETE FROM bookstore.records WHERE Id = #{recordNo}")
    boolean dropRecord(int recordNo);
    //endregion thêm xóa sửa

}
