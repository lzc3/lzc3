package com.lzc.springbootinit;

import com.lzc.springbootinit.mybatis.mapper.EmpMapperTrain;
import com.lzc.springbootinit.mybatis.mapper.EmpMapperXml;
import com.lzc.springbootinit.mybatis.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
class SpringBootInitApplicationTests {

//    @Test
//    void contextLoads() {
//    }

    @Autowired
    private EmpMapperTrain empMapperTrain;

    @Autowired
    private EmpMapperXml empMapperXml;

    @Test
    public void testDelete() {
        int delete = empMapperTrain.delete(16);
        System.out.println(delete);
    }

    @Test
    public void testInsert() {
        Emp emp = Emp.builder()
                .username("Tom3")
                .name("汤姆3")
                .image("1.jpg")
                .gender((short) 1)
                .job((short) 1)
                .entrydate(LocalDate.of(2000, 1, 1))
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deptId(1)
                .build();

        empMapperTrain.insert(emp);
        System.out.println(emp.getId());
    }
    @Test
    public void testUpdate() {
        Emp emp = Emp.builder()
                .id(18)
                .username("Tom1")
                .name("汤姆1")
                .image("1.jpg")
                .gender((short) 1)
                .job((short) 1)
                .entrydate(LocalDate.of(2000, 1, 1))
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deptId(1)
                .build();

        empMapperTrain.update(emp);

    }

    @Test
    public void testGetById() {
        Emp emp = empMapperTrain.getById(17);
        System.out.println(emp.toString());
    }

    @Test
    public void testList() {
        List<Emp> empList = empMapperTrain.list("张",
                (short) 1,
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2020, 1, 1));
        System.out.println(empList);
    }

    @Test
    public void testXmlList() {
//        List<Emp> empList = empMapperXml.list("张",
//                (short) 1,
//                LocalDate.of(2000, 1, 1),
//                LocalDate.of(2020, 1, 1));

        List<Emp> empList = empMapperXml.list(null,
                (short) 1,
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2020, 1, 1));

        System.out.println(empList);
    }

    @Test
    public void testXmlUpdate() {
        Emp emp = Emp.builder()
                .id(18)
                .username("Tom1")
                .entrydate(LocalDate.of(2000, 1, 1))
                .updateTime(LocalDateTime.now())
                .deptId(1)
                .build();
        empMapperXml.update(emp);
    }

    @Test
    public void testXmlDelete() {
        empMapperXml.deleteByIds(Arrays.asList(19, 20));
    }


}
