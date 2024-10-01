package com.lzc.springbootinit.mybatis.mapper;

import com.lzc.springbootinit.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapperTrain {

    /***
     * 删除表中的数据
     * @param id 数据id
     * @return 删除了几条记录
     */
    @Delete("delete from emp where id = #{id}")
    int delete(Integer id);


    @Options(keyProperty = "id")
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
    " values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime},#{updateTime})")
    void insert(Emp emp);

    // 开启驼峰命名自动映射，即从数据库字段名 a_column 映射到Java 属性名 aColumn。
    // mybatis.configuration.map-underscore-to-camel-case=true
    @Update("update emp set username=#{username}, name=#{name}, gender=#{gender}, image=#{image}, " +
            "job=#{job}, entrydate=#{entrydate}, dept_id=#{deptId}, update_time=#{updateTime} where id=#{id}")
    void update(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    @Select("select id, username, password, name, gender, image, job, entrydate, " +
            "dept_id deptId, create_time createTime, update_time updateTime from emp where id = #{id} ")
    Emp getByIdV1(Integer id);

    @Select("select * from emp where id = #{id}")
    @Results({
            @Result(column = "dept_id", property = "deptId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")})
    Emp getByIdV2(Integer id);




    //性能低、不安全、存在SQL注入问题
    // like没法使用 '%#{}%' 因为他会被替换成%?%, 在字符串之中，无法使用
    @Select("select * from emp " +
            "where name like '%${name}%' and " +
            "gender = #{gender} and " +
            "entrydate between #{begin} and #{end} " +
            "order by update_time desc")
    List<Emp> list(String name, Short gender , LocalDate begin , LocalDate end);


    @Select("select * from emp " +
            "where name like  concat('%',#{name},'%') and " +
            "gender = #{gender} and " +
            "entrydate between #{begin} and #{end} " +
            "order by update_time desc")
    List<Emp> listV1(String name, Short gender , LocalDate begin , LocalDate end);


}
