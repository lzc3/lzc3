package com.lzc.springbootinit.controller;

import com.lzc.springbootinit.pojo.TestDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @RestControlle = @Controller + @ResponseBody
 * @ResponseBody: 作用在controller方法上/类上,将方法返回值直接响应，如果返回值是实体对象/集合，将会转换为JSON格式响应
 *
 */
@RestController
public class HelloController {

    /**
     * 通过httpServletRequest接受请求参数
     * @param httpServletRequest 请求servlet
     */
    @RequestMapping("/hello")
    public String hello(HttpServletRequest httpServletRequest) {
        String name = httpServletRequest.getParameter("name");
        String age = httpServletRequest.getParameter("age");

        int ageInt = Integer.parseInt(age);
        return "Hello " + ageInt + " old man " + name;
    }

    /**
     * 接收get请求
     * ?name=lzc&age=10
     */
    @RequestMapping("/springGetDemo-01")
    public String springGetDemo01(String name, Integer age) {
        return "Hello " + age + " year old man " + name;
    }

    /**
     * 接收post请求
     * RequestParam指定接收的参数名称，映射成name
     */
    @RequestMapping("/springPostDemo-01")
    public String springPostDemo01(@RequestParam(name = "username") String name, Integer age) {
        return "Hello " + age + " year old man " + name;
    }

    /**
     * 接收get请求,用对象接收name和age
     * 简单实体对象：请求参数名与形参对象属性名相同，定义pojo接收即可
     */
    @RequestMapping("/springGetDemo-02")
    public String springGetDemo02(TestDto testDto) {
        return "Hello " + testDto.getAge() + " year old man " + testDto.getName();
    }

    /**
     * 接收post请求,用对象接收name和age
     * 简单实体对象：请求参数名与形参对象属性名相同，定义pojo接收即可
     */
    @RequestMapping("/springPostDemo-02")
    public String springPostDemo02(TestDto testDto) {
        return "Hello " + testDto.getAge() + " year old man " + testDto.getName();
    }

    /**
     * 接收get请求,用对象接收name和age, 并且接收对象内的对象
     */
    @RequestMapping("/springGetDemo-03")
    public String springGetDemo03(TestDto testDto) {
        return "Hello " + testDto.getAge() + " year old man " + testDto.getName() + "\n"
                + " have son " + testDto.getTestSonDto().getSonName();
    }

    /**
     * 接收post请求,用对象接收name和age, 并且接收对象内的对象
     */
    @RequestMapping("/springPostDemo-03")
    public String springPostDemo03(TestDto testDto) {
        return "Hello " + testDto.getAge() + " year old man " + testDto.getName() + "\n"
                + " have son " + testDto.getTestSonDto().getSonName();
    }

    /**
     * 接收get请求,用数组接收参数
     */
    @RequestMapping("/springGetDemo-04")
    public String springGetDemo04(String[] name) {
        return "Hello " + name[0] + " nad " + name[1];
    }

    /**
     * 接收get请求,用集合对象接收参数
     * 必须使用RequestParam绑定参数关系，不然会报错
     */
    @RequestMapping("/springGetDemo-05")
    public String springGetDemo05(@RequestParam List<String> name) {
        return "Hello " + name.get(0) + " nad " + name.get(1);
    }

    /**
     * 接收get请求,用日期接收参数
     */
    @RequestMapping("/springGetDemo-06")
    public String springGetDemo06(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateTime) {
        return "now " + dateTime;
    }

    /**
     * 接收post请求,用对象接收json对象
     */
    @RequestMapping("/springPostDemo-04")
    public String springPostDemo0(@RequestBody TestDto testDto) {
        return "Hello " + testDto.getAge() + " year old man " + testDto.getName() + "\n"
                + " have son " + testDto.getTestSonDto().getSonName();
    }

    /**
     * 路径参数
     * 通过请求url直接传递参数，使用{...}来表示该路径参数，使用@PathVariable获取该路径参数
     */
    @RequestMapping("/springGetDemo-07/{name}/{age}")
    public String springGetDemo07(@PathVariable String name, @PathVariable Integer age) {
        return "Hello " + name + "\n" +
                " age is " + age;
    }


}
