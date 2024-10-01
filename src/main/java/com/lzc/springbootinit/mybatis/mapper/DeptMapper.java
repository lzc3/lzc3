package com.lzc.springbootinit.mybatis.mapper;

import com.lzc.springbootinit.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {

    /**
     * 查询全部部门数据
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();

    @Delete("delete from dept where id = #{id}")
    Integer deleteById(Integer id);

    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert into dept (name, create_time, update_time) " +
            "values(#{name}, #{createTime}, #{updateTime})")
    void add(Dept dept);

}
