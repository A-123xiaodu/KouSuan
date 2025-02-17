package cn.bdqn.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface userMapper {
    @Select("select * from name where username=#{username}")
    public String getName(@Param("username") String name);
}
