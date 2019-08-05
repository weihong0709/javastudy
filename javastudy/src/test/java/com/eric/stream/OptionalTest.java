package com.eric.stream;


import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Optional为值得容器，使用Optional可以避免我们一直以来的空指针烦恼
 */
public class OptionalTest {

    /**
     * 测试创建Optional方法
     */
    @Test()
    public void testCreate() {
        Optional<String> optional = Optional.of("test");
        assertEquals("test", optional.get());
        //不能为空,为空抛出空指针异常
        //Optional<String> optionalNull = Optional.of(null);
        //empty创建的是空的optional，ofNullable方法可以把null值创建为空的optional
        Optional<String> empty = Optional.empty();
        Optional<String> optionalNull = Optional.ofNullable(null);
        //空的容器通过get方法去获取值会抛出NoSuchElementException异常
        //empty.get();
        assertThrows(NoSuchElementException.class, () -> empty.get());
        //optionalNull.get();
        assertThrows(NoSuchElementException.class, () -> optionalNull.get());
        assertFalse(optionalNull.isPresent());
        assertFalse(empty.isPresent());
    }

    /**
     * 从optional里面取值，解决了原来需要判断是否为空的问题
     */
    @Test
    public void testGet() {
        Optional<String> optional = Optional.of("test");
        Optional<String> optionalNull = Optional.ofNullable(null);

        assertEquals("test", optional.get());
        //获取Optional中的值，不存在，直接返回"temp"
        assertEquals("temp", optionalNull.orElse("temp"));
        //获取Optional中的值，不存在，对Supplier进行计算，并返回计算结果
        assertEquals("temp2", optionalNull.orElseGet(() -> "temp2"));
        //获取Optional中的值，不存在，抛出自定义异常
        assertThrows(RuntimeException.class, () -> optionalNull.orElseThrow(() -> new RuntimeException("error")), "error ok");
    }
    /**
     * 判断optional里面是否有值
     */
    @Test
    public void testPresent() {
        Optional<String> optional = Optional.of("test");
        Optional<String> optionalNull = Optional.ofNullable(null);
        assertTrue(optional.isPresent());
        //存在则进行处理
        optional.ifPresent(System.out::println);
    }
    /**
     * 用于对 Optional 中的值进行映射处理，从而避免了大量 if 语句嵌套，多个 map 组合成链，
     * 只需对最终的结果进行操作，中间过程中如果存在 null 值，之后的 map 不会执行
     */
    @Test
    public void testMap() {
        Student student = new Student();
        //使用传统方式
        assertEquals("",OptionalExample.getCourseName(student));
        //使用Optional方式
        Optional<Student> optionalStudent = Optional.of(student);
        assertEquals("",OptionalExample.getCourseName(optionalStudent));
        Course course = new Course();
        course.setName("math");
        course.setScore(10);
        student.setCourse(course);
        assertEquals("math",OptionalExample.getCourseName(student));
        assertEquals("math",OptionalExample.getCourseName(optionalStudent));

    }
    /**
     * 通过filter对optional中的值进行过滤，如果不在值就返回empty，存在就进行条件判断，不存在符合的值也返回empty
     * empty的作用也是可以省去if/else语句
     */
    @Test
    public void testFilter() {
        Student student = new Student();
        Course course = new Course();
        course.setName("math");
        course.setScore(10);
        student.setCourse(course);
        //使用Optional方式
        Optional<Student> optionalStudent = Optional.of(student);
        //进行条件判断的函数式接口表达式，需要注意空指针异常 Optional只判断里面存储的元素是否为空，不判断里面包含的元素是否为空
        Optional<Student> resultEmpty= optionalStudent.filter((temp)->temp.getCourse().getName().equals("math2"));
        assertFalse(resultEmpty.isPresent());

        Optional<Student> resultNotEmpty= optionalStudent.filter((temp)->temp.getCourse().getName().equals("math"));
        assertTrue(resultNotEmpty.isPresent());
    }
}
