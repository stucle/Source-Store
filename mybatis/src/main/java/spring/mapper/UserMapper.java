package spring.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import java.util.List;

import spring.model.admin.UserAdmin;

public interface UserMapper
{
    @Select(" SELECT * FROM Users ")
    List<UserAdmin> getAllUser();

    @Select(" SELECT * FROM Users WHERE username = #{username}")
    public UserAdmin getUser(String username);
}
