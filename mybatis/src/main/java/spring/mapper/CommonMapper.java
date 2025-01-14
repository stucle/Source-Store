package spring.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface CommonMapper
{
    public static String GET_NOW_YEAR = "select year(NOW()) AS 'NOW_YEAR'; ";
    @Select(GET_NOW_YEAR)
    public int getNowYear();

    public static String GET_TODAY = " select date(NOW()) AS 'TODAY'; ";
    public Date getToday();

    public static String GET_ALL_RECORDYEARS = " select distinct year(r.Date) AS 'Years' FROM records r; ";
    @Select(GET_ALL_RECORDYEARS)
    public List<Integer> getAllRecordYears();
}
